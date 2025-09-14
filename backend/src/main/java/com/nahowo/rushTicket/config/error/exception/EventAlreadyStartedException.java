package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class EventAlreadyStartedException extends BusinessException {

    public EventAlreadyStartedException() {
        super(ErrorCode.EVENT_ALREADY_STARTED);
    }
}
