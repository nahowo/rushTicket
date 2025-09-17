package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class VenueReservationAlreadyExistException extends BusinessException {

    public VenueReservationAlreadyExistException() {
        super(ErrorCode.VENUE_RESERVATION_ALREADY_EXIST);
    }
}
