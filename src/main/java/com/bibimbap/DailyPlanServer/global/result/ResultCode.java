package com.bibimbap.DailyPlanServer.global.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    // Member
    SAVE_OR_UPDATE_SUCCESS(200, "M001", "회원 정보를 DB에 update 완료하였습니다"),
    GET_USERPROFILE_SUCCESS(200, "M002", "회원 프로필을 조회하였습니다."),
    UPLOAD_MEMBER_IMAGE_SUCCESS(200, "M006", "회원 이미지를 등록하였습니다."),
    DELETE_MEMBER_IMAGE_SUCCESS(200, "M007", "회원 이미지를 삭제하였습니다."),
    GET_EDIT_PROFILE_SUCCESS(200, "M008", "회원 프로필 수정정보를 조회하였습니다."),
    EDIT_PROFILE_SUCCESS(200, "M009", "회원 프로필을 수정하였습니다."),
    LOGOUT_SUCCESS(200, "M020", "로그아웃하였습니다."),


    // DailyPlan
    DAILYPLAN_SAVE_SUCCESS(200, "D001", "데일리 플랜 저장에 성공했습니다"),
    GET_DAILYPLAN_SUCCESS(200, "D002", "데일리 플랜을 조회하였습니다"),

    ;

    private final int status;
    private final String code;
    private final String message;
}