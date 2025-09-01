package com.nahowo.rushTicket.config.result;

import lombok.Getter;

@Getter
public class ResultResponse {
    private String code;
    private String message;
    private Object data;

    public ResultResponse(ResultCode resultCode, Object data) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        this.data = data;
    }

    public static ResultResponse of(ResultCode resultCode, Object data) {
        return new ResultResponse(resultCode, data);
    }
}
