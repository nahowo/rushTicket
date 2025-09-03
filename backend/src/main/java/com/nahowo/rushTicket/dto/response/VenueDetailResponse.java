package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.service.VenueService.VenueStats;
import java.time.LocalDate;
import java.util.TreeMap;
import lombok.Getter;

@Getter
public class VenueDetailResponse {
    private Long id;
    private String name;
    private Integer totalSeats;
    private TreeMap<LocalDate, VenueStats> venueAvailabilities;

    public VenueDetailResponse(Venue venue, TreeMap<LocalDate, VenueStats> availabilities) {
        this.id = venue.getId();
        this.name = venue.getName();
        this.totalSeats = venue.getTotalSeats();
        this.venueAvailabilities = availabilities;
    }
}
