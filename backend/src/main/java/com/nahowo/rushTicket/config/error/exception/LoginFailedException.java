package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;

public class LoginFailedException extends BusinessException {

    public LoginFailedException() {
        super(ErrorCode.INVALID_LOGIN);
    }
}
