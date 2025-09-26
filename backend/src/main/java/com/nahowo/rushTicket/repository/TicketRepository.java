package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

}
