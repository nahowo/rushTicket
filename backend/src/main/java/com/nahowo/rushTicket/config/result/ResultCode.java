package com.nahowo.rushTicket.config.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    USER_CREATE("UR001", "회원 생성 성공"),
    USER_LOGIN("UR002", "회원 로그인 성공"),
    VENUES_VIEW("VR001", "공연장 목록 조회 성공"),
    VENUE_VIEW("VR002", "공연장 조회 성공");

    private final String code;
    private final String message;
}
