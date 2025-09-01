package com.nahowo.rushTicket.config.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {
    USER_CREATE("U001", "회원 생성 성공");

    private final String code;
    private final String message;
}
