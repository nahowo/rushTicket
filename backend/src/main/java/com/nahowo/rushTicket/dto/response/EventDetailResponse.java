package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Event;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record EventDetailResponse (
    Long id,
    LocalDateTime booking_start_time,
    LocalDateTime booking_end_time,
    String description,
    String name,
    List<EventTimeResponse> eventTimeResponses
)
{

    public static EventDetailResponse of(Event event, List<EventTimeResponse> eventTimeResponses) {
        return new EventDetailResponse(event.getId(), event.getBookingStartTime(),
            event.getBookingEndTime(), event.getDescription(), event.getName(),
            eventTimeResponses);
    }

    public record EventDateResponse(
        LocalDate eventDate,
        List<EventTimeResponse> eventTimeResponses
    ) {
    }

    public record EventTimeResponse(
        LocalDateTime eventDateTime,
        List<VenueSeatGroupResponse> venueSeatGroupResponses
    ) {
    }

    public record VenueSeatGroupResponse(
        String groupName,
        Integer seatsCount
    ) {
    }
}
