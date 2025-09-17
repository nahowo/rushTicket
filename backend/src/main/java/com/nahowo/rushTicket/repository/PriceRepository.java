package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.EventDateTime;
import com.nahowo.rushTicket.domain.Price;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {

    List<Price> findAllByEventDateTime(EventDateTime eventDateTime);

}
