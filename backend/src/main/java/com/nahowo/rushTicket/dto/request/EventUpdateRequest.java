package com.nahowo.rushTicket.dto.request;

import java.time.LocalDateTime;

public record EventUpdateRequest (
    LocalDateTime bookingStartTime,
    LocalDateTime bookingEndTime,
    String name,
    String description
) {}
