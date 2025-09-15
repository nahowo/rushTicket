package com.nahowo.rushTicket.config.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "E001", "내부 서버 에러"),
    INVALID_REQUEST(HttpStatus.BAD_REQUEST.value(), "E002", "유효하지 않은 입력"),
    USEREMAIL_DUPLICATED(HttpStatus.CONFLICT.value(), "UE001", "유저 이메일 중복"),
    INVALID_LOGIN(HttpStatus.UNAUTHORIZED.value(), "UE002", "이메일 또는 비밀번호 불일치"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "UE003", "유저를 찾을 수 없음"),
    VENUE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "VE001", "공연장을 찾을 수 없음"),
    EVENT_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "EE001", "공연을 찾을 수 없음"),
    EVENT_ALREADY_STARTED(HttpStatus.BAD_REQUEST.value(), "EE002", "공연이 이미 시작됨"),
    SEAT_GROUP_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "SE001", "좌석 그룹을 찾을 수 없음"),
    EVENT_DATE_TIME_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "EDE001", "공연일시를 찾을 수 없음"),
    VENUE_RESERVATION_ALREADY_EXIST(HttpStatus.BAD_REQUEST.value(), "VRE001", "해당 공연장/일정이 이미 존재함");
    private final int httpStatus;
    private final String code;
    private final String message;
}
