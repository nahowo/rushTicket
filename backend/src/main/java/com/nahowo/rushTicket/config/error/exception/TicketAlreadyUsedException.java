package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class TicketAlreadyUsedException extends BusinessException{

    public TicketAlreadyUsedException() {
        super(ErrorCode.TICKET_ALREADY_USED);
    }
}
