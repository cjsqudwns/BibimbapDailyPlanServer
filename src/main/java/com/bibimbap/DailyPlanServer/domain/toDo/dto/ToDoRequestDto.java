package com.bibimbap.DailyPlanServer.domain.toDo.dto;

import com.bibimbap.DailyPlanServer.domain.toDo.entity.ToDo;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ToDoRequestDto {
    private String title;
    private String alarmStartTime;
    private String alarmEndTime;
    private String categoryCode;

    public ToDo toEntity(){
        return ToDo.builder()
                .title(this.title)
                .alarmStartTime(this.alarmStartTime)
                .alarmEndTime(this.alarmEndTime)
                .build();
    }
}
