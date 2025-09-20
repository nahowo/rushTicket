package com.nahowo.rushTicket.config.error;

import com.nahowo.rushTicket.config.error.exception.BusinessException;

public class SeatStatusNotFoundException extends BusinessException {

    public SeatStatusNotFoundException() {
        super(ErrorCode.SEAT_STATUS_NOT_FOUND);
    }
}
