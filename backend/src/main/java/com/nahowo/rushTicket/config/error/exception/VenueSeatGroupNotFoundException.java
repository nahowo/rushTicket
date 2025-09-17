package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class VenueSeatGroupNotFoundException extends BusinessException {

    public VenueSeatGroupNotFoundException() {
        super(ErrorCode.VENUE_SEAT_GROUP_NOT_FOUND);
    }
}
