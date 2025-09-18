package com.nahowo.rushTicket.service;

import com.nahowo.rushTicket.config.error.exception.VenueNotFoundException;
import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.domain.enums.VenueStatus;
import com.nahowo.rushTicket.dto.response.VenueDetailResponse;
import com.nahowo.rushTicket.dto.response.VenueResponse;
import com.nahowo.rushTicket.repository.VenueReservationRepository;
import com.nahowo.rushTicket.repository.VenueRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.TreeMap;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VenueService {

    private final VenueRepository venueRepository;
    private final VenueReservationRepository venueReservationRepository;

    public List<VenueResponse> getVenues() {
        return venueRepository.findAll()
            .stream().map((VenueResponse::of))
            .toList();
    }

    public VenueDetailResponse getVenueDetail(Long id) {
        Venue venue = venueRepository.findById(id)
            .orElseThrow(VenueNotFoundException::new);
        LocalDate today = LocalDate.now();

        TreeMap<LocalDate, VenueStatus> availabilities = new TreeMap<>();
        for (int i = 0; i <= 30; i++) {
            LocalDate day = today.plusDays(i);
            if (venueReservationRepository.existsByVenueAndEventDate(venue, day)) {
                availabilities.put(day, VenueStatus.RESERVED);
            } else {
                availabilities.put(day, VenueStatus.AVAILABLE);
            }
        }
        return VenueDetailResponse.of(venue, availabilities);
    }
}
