package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class EventBookingAlreadyStartedException extends BusinessException {

    public EventBookingAlreadyStartedException() {
        super(ErrorCode.EVENT_BOOKING_ALREADY_STARTED);
    }
}
