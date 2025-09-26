package com.nahowo.rushTicket.domain;

import com.nahowo.rushTicket.dto.request.EventUpdateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Entity
@Table(name = "events")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@SQLRestriction("is_deleted = false")
public class Event extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "booking_start_time", nullable = false)
    private LocalDateTime bookingStartTime;

    @Column(name = "booking_end_time", nullable = false)
    private LocalDateTime bookingEndTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventStatus status;

    public void update(EventUpdateRequest request) {
        if (request.bookingStartTime() != null) {
            this.bookingStartTime = request.bookingStartTime();
        }
        if (request.bookingEndTime() != null) {
            this.bookingEndTime = request.bookingEndTime();
        }
        if (request.name() != null) {
            this.name = request.name();
        }
        if (request.description() != null) {
            this.description = request.description();
        }
    }

    public enum EventStatus {
        ACTIVE, CANCELED
    }
}
