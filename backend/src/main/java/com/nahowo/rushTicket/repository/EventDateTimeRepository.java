package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.Event;
import com.nahowo.rushTicket.domain.EventDateTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDateTimeRepository extends JpaRepository<EventDateTime, Long> {

    Optional<EventDateTime> findByEventStartTime(LocalDateTime eventStartTime);

    List<EventDateTime> findAllByEvent(Event event);
}
