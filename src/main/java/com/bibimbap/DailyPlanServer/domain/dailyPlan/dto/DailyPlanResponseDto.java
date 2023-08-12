package com.bibimbap.DailyPlanServer.domain.dailyPlan.dto;

import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import com.bibimbap.DailyPlanServer.domain.toDo.entity.ToDo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class DailyPlanResponseDto {
    private String yearMonth;
    private String date;
    private List<ToDo> toDoResponseDtos;

    @Builder
    public DailyPlanResponseDto(DailyPlan entity){
        this.yearMonth = entity.getYearMonth();
        this.date = entity.getDate();
        this.toDoResponseDtos = entity.getToDo();
    }
}
