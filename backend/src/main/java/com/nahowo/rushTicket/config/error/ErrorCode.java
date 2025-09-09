package com.nahowo.rushTicket.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "E001", "내부 서버 에러"),
    USEREMAIL_DUPLICATED(HttpStatus.CONFLICT.value(), "UE001", "유저 이메일 중복"),
    INVALID_LOGIN(HttpStatus.UNAUTHORIZED.value(), "UE002", "이메일 또는 비밀번호 불일치"),
    VENUE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "VE001", "공연장을 찾을 수 없음"),
    EVENT_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "EE001", "공연을 찾을 수 없음"),
    SEAT_GROUP_NOT_FOUND(HttpStatus.NO_CONTENT.value(), "SE001", "좌석 그룹을 찾을 수 없음");
    private final int httpStatus;
    private final String code;
    private final String message;
}
