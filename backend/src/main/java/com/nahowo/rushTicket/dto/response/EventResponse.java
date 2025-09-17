package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Event;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class EventResponse {
    private Long id;
    private LocalDateTime booking_start_time;
    private LocalDateTime booking_end_time;
    private String description;
    private String name;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.booking_start_time = event.getBookingStartTime();
        this.booking_end_time = event.getBookingEndTime();
        this.description = event.getDescription();
        this.name = event.getName();
    }
}
