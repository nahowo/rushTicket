package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
