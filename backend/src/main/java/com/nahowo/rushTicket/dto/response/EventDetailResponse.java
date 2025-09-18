package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Event;
import com.nahowo.rushTicket.domain.EventDateTime;
import com.nahowo.rushTicket.domain.VenueSeatGroup;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EventDetailResponse (
    Long id,
    LocalDateTime booking_start_time,
    LocalDateTime booking_end_time,
    String description,
    String name,
    List<EventDateResponse> eventDateResponses,
    List<VenueSeatGroupResponse> venueSeatGroupResponses
)
{
    public static EventDetailResponse of(Event event, List<EventDateResponse> eventDateResponses, List<VenueSeatGroupResponse> venueSeatGroupResponses) {
        return new EventDetailResponse(event.getId(), event.getBookingStartTime(),
            event.getBookingEndTime(), event.getDescription(), event.getName(),
            eventDateResponses, venueSeatGroupResponses);
    }

    public record EventDateResponse(
        LocalDate eventDate,
        List<LocalDateTime> eventDateTimes
    ) {

        public static EventDateResponse of(LocalDate eventDate, List<LocalDateTime> eventDateTimes) {
            return new EventDateResponse(eventDate, eventDateTimes);
        }
    }

    public record VenueSeatGroupResponse(
        String groupName,
        Integer seatsCount
    ) {

        public static VenueSeatGroupResponse of(VenueSeatGroup venueSeatGroup) {
            return new VenueSeatGroupResponse(venueSeatGroup.getGroupName(),
                venueSeatGroup.getSeatsCount());
        }
    }
}
