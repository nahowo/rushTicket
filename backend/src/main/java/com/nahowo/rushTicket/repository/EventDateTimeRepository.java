package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.EventDateTime;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDateTimeRepository extends JpaRepository<EventDateTime, Long> {

    Optional<EventDateTime> findByEventStartTime(LocalDateTime eventStartTime);
}
