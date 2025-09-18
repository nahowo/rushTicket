package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.domain.enums.VenueStatus;
import java.time.LocalDate;
import java.util.TreeMap;

public record VenueDetailResponse (
    Long id,
    String name,
    Integer totalSeats,
    TreeMap<LocalDate, VenueStatus> venueAvailabilities
) {
    public static VenueDetailResponse of(Venue venue, TreeMap<LocalDate, VenueStatus> availabilities) {
        return new VenueDetailResponse(venue.getId(), venue.getName(), venue.getTotalSeats(),
            availabilities);
    }
}
