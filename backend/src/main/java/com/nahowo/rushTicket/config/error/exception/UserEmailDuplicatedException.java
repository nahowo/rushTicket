package com.nahowo.rushTicket.config.error.exception;

import com.nahowo.rushTicket.config.error.ErrorCode;
import lombok.Getter;

@Getter
public class UserEmailDuplicatedException extends BusinessException {
    public UserEmailDuplicatedException() {
        super(ErrorCode.USEREMAIL_DUPLICATED);
    }

}
