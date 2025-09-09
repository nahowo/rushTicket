package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class SeatGroupNotFoundException extends BusinessException{

    public SeatGroupNotFoundException() {
        super(ErrorCode.SEAT_GROUP_NOT_FOUND);
    }
}
