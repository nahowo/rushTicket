package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.dto.response.VenueResponse;
import com.nahowo.rushTicket.repository.VenueRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;

    public List<VenueResponse> getVenues() {
        return venueRepository.findAll()
            .stream().map((VenueResponse::new))
            .toList();
    }
}
