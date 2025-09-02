package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.Venue;
import lombok.Getter;

@Getter
public class VenueResponse {
    private Long id;
    private String name;
    private Integer totalSeats;

    public VenueResponse(Venue venue) {
        this.id = venue.getId();
        this.name = venue.getName();
        this.totalSeats = venue.getTotalSeats();
    }
}
