package com.nahowo.rushTicket.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "seat_status")
public class SeatStatus extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_date_time_id", nullable = false)
    private EventDateTime eventDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_id", nullable = false)
    private Seat seat;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        AVAILABLE, BOOKED, LOCKED
    }

    public void bookSeat() {
        this.status = Status.BOOKED;
    }

    public void cancelSeat() {
        this.status = Status.AVAILABLE;
    }
}
