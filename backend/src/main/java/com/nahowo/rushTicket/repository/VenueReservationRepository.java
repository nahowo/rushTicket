package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.domain.VenueReservation;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueReservationRepository extends JpaRepository<VenueReservation, Long> {
    boolean existsByVenueAndEventDate(Venue venue, LocalDate eventDate);
}
