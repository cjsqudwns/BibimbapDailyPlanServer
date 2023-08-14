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
    private Long dailyPlanId;
    private String yearMonth;
    private int day;
    @Builder
    public DailyPlanResponseDto(DailyPlan entity){
        this.dailyPlanId = entity.getId();
        this.yearMonth = entity.getYearMonth();
        this.day = entity.getDay();
    }
}
