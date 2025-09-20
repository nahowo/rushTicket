package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class TicketNotFoundException extends BusinessException{

    public TicketNotFoundException() {
        super(ErrorCode.TICKET_NOT_FOUND);
    }
}
