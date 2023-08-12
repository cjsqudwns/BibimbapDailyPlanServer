package com.bibimbap.DailyPlanServer.domain.toDo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoRequestDto {
    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String categoryCode;

    @Builder
    public ToDoRequestDto(String title, String alarmStartTime, String alarmEndTime, String categoryCode){
        this.title = title;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
        this.categoryCode = categoryCode;
    }
}
