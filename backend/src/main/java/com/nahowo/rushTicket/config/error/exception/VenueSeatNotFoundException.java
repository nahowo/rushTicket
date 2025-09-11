package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class VenueSeatNotFoundException extends BusinessException {

    public VenueSeatNotFoundException() {
        super(ErrorCode.SEAT_GROUP_NOT_FOUND);
    }
}
