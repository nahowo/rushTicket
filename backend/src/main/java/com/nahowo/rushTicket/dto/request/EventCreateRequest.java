package com.nahowo.rushTicket.dto.request;

import com.nahowo.rushTicket.dto.annotation.ValidBookingTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ValidBookingTime
public record EventCreateRequest (
    @NotNull(message = "판매자 id는 필수입니다. ") Long userId,
    @NotEmpty(message = "공연 일정/좌석 정보는 필수입니다. ") @Valid List<EventTimeAndPrice> eventTimeAndPrices,
    @NotNull(message = "공연장 id는 필수입니다. ") Long venueId,
    @NotNull(message = "예매 시작 일시는 필수입니다. ") LocalDateTime bookingStartTime,
    @NotNull(message = "예매 종료 일시는 필수입니다. ") LocalDateTime bookingEndTime,
    @NotBlank(message = "공연 이름은 필수입니다. ") String name,
    String description
) {

    public record EventTimeAndPrice (
        @NotNull(message = "공연 일자는 필수입니다. ") LocalDate eventDate,
        @NotEmpty(message = "공연일시/좌석 그룹별 금액은 필수입니다. ") @Valid List<DateSeatGroupPrice> dateSeatGroupPrices
    ) {}

    public record DateSeatGroupPrice(
        @NotNull(message = "공연 시작 일시는 필수입니다. ") LocalDateTime eventStartTime,
        @NotNull(message = "공연 종료 일시는 필수입니다. ") LocalDateTime eventEndTime,
        @NotEmpty(message = "공연 좌석별 금액은 필수입니다. ") @Valid List<SeatGroupPrice> seatGroupPrices
    ) {}

    public record SeatGroupPrice (
        @NotNull(message = "공연장 좌석 그룹 id는 필수입니다. ") Long venueSeatGroupId,
        @NotNull(message = "좌석 금액은 필수입니다. ") BigDecimal price
    ) {}
}
