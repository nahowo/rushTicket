package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.domain.enums.VenueStatus;
import java.time.LocalDate;
import java.util.TreeMap;
import lombok.Getter;

@Getter
public class VenueDetailResponse {
    private Long id;
    private String name;
    private Integer totalSeats;
    private TreeMap<LocalDate, VenueStatus> venueAvailabilities;

    public VenueDetailResponse(Venue venue, TreeMap<LocalDate, VenueStatus> availabilities) {
        this.id = venue.getId();
        this.name = venue.getName();
        this.totalSeats = venue.getTotalSeats();
        this.venueAvailabilities = availabilities;
    }
}
