package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.Seat;
import com.nahowo.rushTicket.domain.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {

    Seat findByVenue(Venue venue);
}
