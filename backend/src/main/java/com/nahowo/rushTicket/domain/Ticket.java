package com.nahowo.rushTicket.domain;

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

@Entity
@Table(name = "tickets")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Ticket extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id", nullable = false)
    private Price price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seat_status_id", nullable = false)
    private SeatStatus seatStatus;

    @Column(name = "qr_code", nullable = false, unique = true)
    private String qrCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TicketStatus status;

    @Column(name = "purchase_time", nullable = false)
    private LocalDateTime purchaseTime;

    public enum TicketStatus {
        BOOKED, USED, CANCELED
    }

    public void cancelTicket() {
        this.status = TicketStatus.CANCELED;
    }

    public void useTicket() {
        this.status = TicketStatus.USED;
    }
}
