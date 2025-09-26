package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Event;
import java.time.LocalDateTime;

public record EventResponse (
    Long id,
    LocalDateTime booking_start_time,
    LocalDateTime booking_end_time,
    String description,
    String name
    ) {

    public static EventResponse of(Event event) {
        return new EventResponse(event.getId(), event.getBookingStartTime(),
            event.getBookingEndTime(), event.getDescription(), event.getName());
    }
}
