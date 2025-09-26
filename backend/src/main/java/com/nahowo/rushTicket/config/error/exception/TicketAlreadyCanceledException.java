package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class TicketAlreadyCanceledException extends BusinessException {

    public TicketAlreadyCanceledException() {
        super(ErrorCode.TICKET_ALREADY_CANCELED);
    }
}
