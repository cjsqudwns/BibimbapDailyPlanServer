package com.bibimbap.DailyPlanServer.global.error.exception;

import com.bibimbap.DailyPlanServer.global.error.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
