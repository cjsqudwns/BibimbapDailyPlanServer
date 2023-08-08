package com.bibimbap.DailyPlanServer.global.error;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ErrorResponse {

    private final String message;
    private final String code;
    private final int httpStatus;
    private String detail;
    private final LocalDateTime timestamp;

    public ErrorResponse(ErrorCode code, LocalDateTime localDateTime){
        this.message = code.getMessage();
        this.code = code.getCode();
        this.httpStatus = code.getStatus();
        this.timestamp = localDateTime;
    }

    public static ErrorResponse of(ErrorCode code, LocalDateTime localDateTime) {
        return new ErrorResponse(code, localDateTime);
    }

    public void setDetail(String detailMessage) {
        this.detail = detailMessage;
    }
}
