package com.bibimbap.DailyPlanServer.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Member
    MEMBER_NOT_FOUND(400, "M001", "존재 하지 않는 유저입니다."),
    USERNAME_ALREADY_EXIST(400, "M002", "이미 존재하는 사용자 이름입니다."),
    ACCOUNT_MISMATCH(401, "M005", "계정 정보가 일치하지 않습니다."),

    //DailyPlan
    DAILYPLAN_ALREADY_EXIST(400, "D001", "이미 데일리 플랜을 작성하였습니다. PUT으로 update 해주세요"),
    DAILYPLAN_NOT_FOUND(400, "D002", "존재 하지 않는 데일리 플랜입니다"),;

    private int status;
    private String code;
    private String message;
}
