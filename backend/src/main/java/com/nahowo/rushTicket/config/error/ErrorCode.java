package com.nahowo.rushTicket.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "S001", "내부 서버 에러"),
    USEREMAIL_DUPLICATED(HttpStatus.CONFLICT.value(), "U001", "유저 이메일 중복");
    private final int httpStatus;
    private final String code;
    private final String message;
}
