package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.EventDateTime;
import com.nahowo.rushTicket.domain.Seat;
import com.nahowo.rushTicket.domain.SeatStatus;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatStatusRepository extends JpaRepository<SeatStatus, Long> {

    List<SeatStatus> findAllBySeatAndEventDateTime(Seat seat, EventDateTime eventDateTime);
}
