package com.bibimbap.DailyPlanServer.domain.toDo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoUpdateDto {
    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String afterCategoryCode;

    @Builder
    public ToDoUpdateDto(String title, String alarmStartTime, String alarmEndTime, String afterCategoryCode){
        this.title = title;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
        this.afterCategoryCode = afterCategoryCode;
    }
}
