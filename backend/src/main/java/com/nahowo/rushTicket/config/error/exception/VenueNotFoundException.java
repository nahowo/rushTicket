package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class VenueNotFoundException extends BusinessException {

    public VenueNotFoundException() {
        super(ErrorCode.VENUE_NOT_FOUND);
    }
}
