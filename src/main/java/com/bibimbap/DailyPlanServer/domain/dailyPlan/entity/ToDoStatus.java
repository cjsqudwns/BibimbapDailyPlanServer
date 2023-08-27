package com.bibimbap.DailyPlanServer.domain.dailyPlan.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ToDoStatus {
    FAIL("실패", "F"),
    COMPLETE("성공", "C"),
    IN_PROGRESS("진행중", "P")
    ;

    private String status;
    private String code;
}
