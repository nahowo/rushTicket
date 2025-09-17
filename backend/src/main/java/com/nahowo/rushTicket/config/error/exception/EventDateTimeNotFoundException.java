package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class EventDateTimeNotFoundException extends BusinessException {

    public EventDateTimeNotFoundException() {
        super(ErrorCode.EVENT_DATE_TIME_NOT_FOUND);
    }
}
