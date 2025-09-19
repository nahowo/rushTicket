package com.nahowo.rushTicket.dto.response;

import com.nahowo.rushTicket.domain.SeatStatus;
import com.nahowo.rushTicket.domain.SeatStatus.Status;
import java.util.List;

public record SeatStatusesResponse(
    List<SeatStatusResponse> seatStatuses
) {
    public record SeatStatusResponse (
        Long id,
        Status status,
        Long seatId,
        Long eventDateTimeId
    ) {

        public static SeatStatusResponse of(SeatStatus seatStatus) {
            return new SeatStatusResponse(seatStatus.getId(), seatStatus.getStatus(),
                seatStatus.getSeat().getId(), seatStatus.getEventDateTime().getId());
        }
    }
}
