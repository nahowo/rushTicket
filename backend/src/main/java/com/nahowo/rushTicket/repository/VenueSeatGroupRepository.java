package com.nahowo.rushTicket.repository;

import com.nahowo.rushTicket.domain.Venue;
import com.nahowo.rushTicket.domain.VenueSeatGroup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueSeatGroupRepository extends JpaRepository<VenueSeatGroup, Long> {

    List<VenueSeatGroup> findByVenue(Venue venue);
}
