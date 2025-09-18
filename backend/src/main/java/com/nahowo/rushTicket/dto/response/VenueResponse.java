package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Venue;

public record VenueResponse (
    Long id,
    String name,
    Integer totalSeats
) {
    public static VenueResponse of(Venue venue) {
        return new VenueResponse(venue.getId(), venue.getName(), venue.getTotalSeats());
    }
}
