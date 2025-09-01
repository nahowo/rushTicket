package com.nahowo.rushTicket.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "SE001", "내부 서버 에러"),
    USEREMAIL_DUPLICATED(HttpStatus.CONFLICT.value(), "UE001", "유저 이메일 중복"),
    INVALID_LOGIN(HttpStatus.NOT_FOUND.value(), "UE002", "이메일 또는 비밀번호 불일치");
    private final int httpStatus;
    private final String code;
    private final String message;
}
