package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.config.error.exception.EventDateTimeNotFoundException;
import com.nahowo.rushTicket.config.error.exception.EventNotFoundException;
import com.nahowo.rushTicket.config.error.exception.UserNotFoundException;
import com.nahowo.rushTicket.config.error.exception.VenueNotFoundException;
import com.nahowo.rushTicket.config.error.exception.VenueSeatNotFoundException;
import com.nahowo.rushTicket.domain.Event;
import com.nahowo.rushTicket.domain.Event.EventStatus;
import com.nahowo.rushTicket.domain.EventDateTime;
import com.nahowo.rushTicket.domain.Price;
import com.nahowo.rushTicket.domain.User;
import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.domain.VenueReservation;
import com.nahowo.rushTicket.domain.VenueSeatGroup;
import com.nahowo.rushTicket.dto.request.EventCreateRequest;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.DateSeatGroupPrice;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.EventTimeAndPrice;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.SeatGroupPrice;
import com.nahowo.rushTicket.dto.request.EventUpdateRequest;
import com.nahowo.rushTicket.dto.response.EventResponse;
import com.nahowo.rushTicket.repository.EventDateTimeRepository;
import com.nahowo.rushTicket.repository.EventRepository;
import com.nahowo.rushTicket.repository.PriceRepository;
import com.nahowo.rushTicket.repository.UserRepository;
import com.nahowo.rushTicket.repository.VenueRepository;
import com.nahowo.rushTicket.repository.VenueReservationRepository;
import com.nahowo.rushTicket.repository.VenueSeatGroupRepository;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventService {

    private final UserRepository userRepository;
    private final VenueRepository venueRepository;
    private final VenueReservationRepository venueReservationRepository;
    private final EventRepository eventRepository;
    private final EventDateTimeRepository eventDateTimeRepository;
    private final VenueSeatGroupRepository venueSeatGroupRepository;
    private final PriceRepository priceRepository;

    @Transactional
    public EventResponse createEvent(EventCreateRequest request) {
        // todo: 예외 상황 처리하기, 세부 함수로 분리하기
        // 판매자 id 조회
        Long userId = request.userId();
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);

        // 공연장 id 조회
        Long venueId = request.venueId();
        Venue venue = venueRepository.findById(venueId).orElseThrow(VenueNotFoundException::new);

        // venue_reservation 튜플 추가
        List<EventTimeAndPrice> eventTimeAndPrices = request.eventTimeAndPrices();
        for (EventTimeAndPrice eventTimeAndPrice : eventTimeAndPrices) {
            VenueReservation venueReservation = new VenueReservation(venue, eventTimeAndPrice.eventDate());
            venueReservationRepository.save(venueReservation);
        }

        // events 튜플 추가
        String name = request.name();
        String description = request.description();
        LocalDateTime bookingStartTime = request.bookingStartTime();
        LocalDateTime bookingEndTime = request.bookingEndTime();
        EventStatus status = EventStatus.ACTIVE;
        Event event = new Event(user, venue, name, description, bookingStartTime, bookingEndTime,
            status);
        eventRepository.save(event);

        // event_date_times 튜플 추가
        for (EventTimeAndPrice eventTimeAndPrice : eventTimeAndPrices) {
            List<DateSeatGroupPrice> dateSeatGroupPrices = eventTimeAndPrice.dateSeatGroupPrices();
            for (DateSeatGroupPrice dateSeatGroupPrice : dateSeatGroupPrices) {
                EventDateTime eventDateTime = new EventDateTime(event,
                    dateSeatGroupPrice.eventStartTime(), dateSeatGroupPrice.eventEndTime());
                EventDateTime savedEventDateTime = eventDateTimeRepository.save(eventDateTime);

                List<SeatGroupPrice> seatGroupPrices = dateSeatGroupPrice.seatGroupPrices();
                for (SeatGroupPrice seatGroupPrice : seatGroupPrices) {
                    // 좌석 그룹 id 조회/검증
                    Long venueSeatGroupId = seatGroupPrice.venueSeatGroupId();
                    VenueSeatGroup venueSeatGroup = venueSeatGroupRepository.findById(
                            venueSeatGroupId)
                        .orElseThrow(VenueSeatNotFoundException::new);

                    // prices 튜플 추가
                    BigDecimal seatPrice = seatGroupPrice.price();
                    Price price = new Price(savedEventDateTime, venueSeatGroup, seatPrice);
                    priceRepository.save(price);
                }
            }
        }
        return new EventResponse(event);
    }

    @Transactional
    public EventResponse updateEvent(Long eventId, EventUpdateRequest request) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        event.update(request);
        return new EventResponse(event);
    }
}
