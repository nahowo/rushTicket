package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.config.error.SeatStatusNotFoundException;
import com.nahowo.rushTicket.config.error.exception.EventAlreadyStartedException;
import com.nahowo.rushTicket.config.error.exception.EventBookingAlreadyStartedException;
import com.nahowo.rushTicket.config.error.exception.EventDateTimeNotFoundException;
import com.nahowo.rushTicket.config.error.exception.EventNotFoundException;
import com.nahowo.rushTicket.config.error.exception.TicketNotFoundException;
import com.nahowo.rushTicket.config.error.exception.UserNotFoundException;
import com.nahowo.rushTicket.config.error.exception.VenueNotFoundException;
import com.nahowo.rushTicket.config.error.exception.VenueReservationAlreadyExistException;
import com.nahowo.rushTicket.config.error.exception.VenueSeatGroupNotFoundException;
import com.nahowo.rushTicket.domain.Event;
import com.nahowo.rushTicket.domain.Event.EventStatus;
import com.nahowo.rushTicket.domain.EventDateTime;
import com.nahowo.rushTicket.domain.Price;
import com.nahowo.rushTicket.domain.Seat;
import com.nahowo.rushTicket.domain.SeatStatus;
import com.nahowo.rushTicket.domain.Ticket;
import com.nahowo.rushTicket.domain.Ticket.TicketStatus;
import com.nahowo.rushTicket.domain.User;
import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.domain.VenueReservation;
import com.nahowo.rushTicket.domain.VenueSeatGroup;
import com.nahowo.rushTicket.dto.request.EventCreateRequest;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.DateSeatGroupPrice;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.EventTimeAndPrice;
import com.nahowo.rushTicket.dto.request.EventCreateRequest.SeatGroupPrice;
import com.nahowo.rushTicket.dto.request.EventUpdateRequest;
import com.nahowo.rushTicket.dto.response.EventDetailResponse;
import com.nahowo.rushTicket.dto.response.EventDetailResponse.EventDateResponse;
import com.nahowo.rushTicket.dto.response.EventDetailResponse.VenueSeatGroupResponse;
import com.nahowo.rushTicket.dto.response.EventResponse;
import com.nahowo.rushTicket.dto.response.SeatStatusesResponse;
import com.nahowo.rushTicket.dto.response.SeatStatusesResponse.SeatStatusResponse;
import com.nahowo.rushTicket.repository.EventDateTimeRepository;
import com.nahowo.rushTicket.repository.EventRepository;
import com.nahowo.rushTicket.repository.PriceRepository;
import com.nahowo.rushTicket.repository.SeatRepository;
import com.nahowo.rushTicket.repository.SeatStatusRepository;
import com.nahowo.rushTicket.repository.TicketRepository;
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
    private final SeatRepository seatRepository;
    private final SeatStatusRepository seatStatusRepository;
    private final TicketRepository ticketRepository;

    @Transactional
    public List<EventResponse> viewsEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
            .map(EventResponse::of)
            .toList();
    }

    @Transactional
    public EventDetailResponse viewEvent(Long eventId) {
        Event event = eventRepository.findById(eventId)
            .orElseThrow(EventNotFoundException::new);

        List<EventDateTime> eventDateTimes = eventDateTimeRepository.findAllByEvent(event);
        Map<LocalDate, List<LocalDateTime>> eventDateMap = new TreeMap<>();
        for (EventDateTime eventDateTime : eventDateTimes) {
            LocalDate localDate = eventDateTime.getEventStartTime().toLocalDate();
            LocalDateTime eventStartTime = eventDateTime.getEventStartTime();
            if (eventDateMap.containsKey(localDate)) {
                eventDateMap.get(localDate).add(eventStartTime);
            } else {
                eventDateMap.put(localDate, List.of(eventStartTime));
            }
        }
        List<EventDateResponse> eventDateResponses = eventDateMap.entrySet().stream()
            .map(entry -> EventDateResponse.of(entry.getKey(), entry.getValue()))
            .toList();

        Venue venue = event.getVenue();
        List<VenueSeatGroupResponse> venueSeatGroupResponses = venueSeatGroupRepository.findByVenue(venue).stream()
            .map(VenueSeatGroupResponse::of)
            .toList();
        return EventDetailResponse.of(event, eventDateResponses, venueSeatGroupResponses);
    }

    @Transactional
    public SeatStatusesResponse viewSeatStatus(Long eventId, Long eventDateTimeId) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        Venue venue = event.getVenue();
        Seat seat = seatRepository.findByVenue(venue);
        EventDateTime eventDateTime = eventDateTimeRepository.findById(eventDateTimeId).orElseThrow(
            EventDateTimeNotFoundException::new);
        List<SeatStatusResponse> seatStatusResponses = seatStatusRepository.findAllBySeatAndEventDateTime(
                seat, eventDateTime).stream()
            .map(SeatStatusResponse::of)
            .toList();
        return new SeatStatusesResponse(seatStatusResponses);
    }

    @Transactional
    public SeatStatusResponse bookSeat(Long userId, Long seatStatusId) {
        SeatStatus seatStatus = seatStatusRepository.findById(seatStatusId)
            .orElseThrow(SeatStatusNotFoundException::new);
        seatStatus.bookSeat();
        EventDateTime eventDateTime = seatStatus.getEventDateTime();
        VenueSeatGroup venueSeatGroup = seatStatus.getSeat().getVenueSeatGroup();
        Price price = priceRepository.findByEventDateTimeAndVenueSeatGroup(
            eventDateTime, venueSeatGroup);

        User user = getUser(userId);
        Ticket ticket = new Ticket(user, price, seatStatus, null, TicketStatus.BOOKED,
            LocalDateTime.now());
        ticketRepository.save(ticket);
        return SeatStatusResponse.of(seatStatus);
    }

    @Transactional
    public void cancelSeat(Long ticketId) {
        Ticket ticket = ticketRepository.findById(ticketId)
            .orElseThrow(TicketNotFoundException::new);
        ticket.cancelTicket();

        SeatStatus seatStatus = ticket.getSeatStatus();
        seatStatus.cancelSeat();
    }

    @Transactional
    public EventResponse createEvent(EventCreateRequest request) {
        User user = getUser(request.userId());
        Venue venue = getVenue(request.venueId());

        List<EventTimeAndPrice> eventTimeAndPrices = createEventTimeAndPrices(
            request, venue);
        Event event = createEvent(request, user, venue);
        for (EventTimeAndPrice eventTimeAndPrice : eventTimeAndPrices) {
            createEventDateTimes(eventTimeAndPrice, event);
        }
        return EventResponse.of(event);
    }

    @Transactional
    public EventResponse updateEvent(Long eventId, EventUpdateRequest request) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        if (checkBookingStarted(event)) {
            throw new EventBookingAlreadyStartedException();
        }
        event.update(request);
        return EventResponse.of(event);
    }

    @Transactional
    public void deleteEvent(Long eventId) {
        Event event = eventRepository.findById(eventId).orElseThrow(EventNotFoundException::new);
        if (checkEventStarted(event)) {
            throw new EventAlreadyStartedException();
        }
        Venue venue = event.getVenue();
        event.delete();
        deleteEventDateTimes(event, venue);
    }

    private void createEventDateTimes(EventTimeAndPrice eventTimeAndPrice, Event event) {
        List<DateSeatGroupPrice> dateSeatGroupPrices = eventTimeAndPrice.dateSeatGroupPrices();
        for (DateSeatGroupPrice dateSeatGroupPrice : dateSeatGroupPrices) {
            EventDateTime savedEventDateTime = createEventDateTime(event,
                dateSeatGroupPrice);

            List<SeatGroupPrice> seatGroupPrices = dateSeatGroupPrice.seatGroupPrices();
            for (SeatGroupPrice seatGroupPrice : seatGroupPrices) {
                VenueSeatGroup venueSeatGroup = getVenueSeatGroup(seatGroupPrice);
                createPrices(seatGroupPrice, savedEventDateTime, venueSeatGroup);
            }
        }
    }

    private EventDateTime createEventDateTime(Event event, DateSeatGroupPrice dateSeatGroupPrice) {
        EventDateTime eventDateTime = new EventDateTime(event,
            dateSeatGroupPrice.eventStartTime(), dateSeatGroupPrice.eventEndTime());
        EventDateTime savedEventDateTime = eventDateTimeRepository.save(eventDateTime);
        return savedEventDateTime;
    }

    private void createPrices(SeatGroupPrice seatGroupPrice, EventDateTime savedEventDateTime,
        VenueSeatGroup venueSeatGroup) {
        BigDecimal seatPrice = seatGroupPrice.price();
        Price price = new Price(savedEventDateTime, venueSeatGroup, seatPrice);
        priceRepository.save(price);
    }

    private VenueSeatGroup getVenueSeatGroup(SeatGroupPrice seatGroupPrice) {
        Long venueSeatGroupId = seatGroupPrice.venueSeatGroupId();
        return venueSeatGroupRepository.findById(
                venueSeatGroupId)
            .orElseThrow(VenueSeatGroupNotFoundException::new);
    }

    private Event createEvent(EventCreateRequest request, User user, Venue venue) {
        String name = request.name();
        String description = request.description();
        LocalDateTime bookingStartTime = request.bookingStartTime();
        LocalDateTime bookingEndTime = request.bookingEndTime();
        EventStatus status = EventStatus.ACTIVE;
        Event event = new Event(user, venue, name, description, bookingStartTime, bookingEndTime,
            status);
        eventRepository.save(event);
        return event;
    }

    private List<EventTimeAndPrice> createEventTimeAndPrices(EventCreateRequest request, Venue venue) {
        List<EventTimeAndPrice> eventTimeAndPrices = request.eventTimeAndPrices();
        createVenueReservations(venue, eventTimeAndPrices);
        return eventTimeAndPrices;
    }

    private void createVenueReservations(Venue venue, List<EventTimeAndPrice> eventTimeAndPrices) {
        for (EventTimeAndPrice eventTimeAndPrice : eventTimeAndPrices) {
            createVenueReservation(venue, eventTimeAndPrice);
        }
    }

    private void createVenueReservation(Venue venue, EventTimeAndPrice eventTimeAndPrice) {
        LocalDate eventDate = eventTimeAndPrice.eventDate();
        isVenueReservationExist(venue, eventDate);
        VenueReservation venueReservation = new VenueReservation(venue, eventDate);
        venueReservationRepository.save(venueReservation);
    }

    private Venue getVenue(Long venueId) {
        return venueRepository.findById(venueId).orElseThrow(VenueNotFoundException::new);
    }

    private User getUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    private boolean checkBookingStarted(Event event) {
        return !event.getBookingStartTime().isAfter(LocalDateTime.now());
    }

    private boolean checkEventStarted(Event event) {
        List<EventDateTime> eventDateTimes = eventDateTimeRepository.findAllByEvent(event);
        LocalDateTime now = LocalDateTime.now();
        for (EventDateTime eventDateTime : eventDateTimes) {
            if (!eventDateTime.getEventStartTime().isAfter(now)) {
                return true;
            }
        }
        return false;
    }

    private void deleteEventDateTimes(Event event, Venue venue) {
        List<EventDateTime> eventDateTimes = eventDateTimeRepository.findAllByEvent(event);
        for (EventDateTime eventDatetime : eventDateTimes) {
            deleteEventDateTime(venue, eventDatetime);
        }
    }

    private void deleteEventDateTime(Venue venue, EventDateTime eventDatetime) {
        deletePrices(eventDatetime);
        deleteVenueReservation(venue, eventDatetime);
        eventDatetime.delete();
    }

    private void deleteVenueReservation(Venue venue, EventDateTime eventDatetime) {
        LocalDate eventDate = eventDatetime.getEventStartTime().toLocalDate();
        VenueReservation venueReservation = venueReservationRepository.findByEventDateAndVenue(
            eventDate, venue);
        venueReservation.delete();
    }

    private void deletePrices(EventDateTime eventDatetime) {
        List<Price> prices = priceRepository.findAllByEventDateTime(eventDatetime);
        for (Price price : prices) {
            price.delete();
        }
    }

    private void isVenueReservationExist(Venue venue, LocalDate eventDate) {
        if (venueReservationRepository.existsByVenueAndEventDate(venue, eventDate)) {
            throw new VenueReservationAlreadyExistException();
        }
    }
}
