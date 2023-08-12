package com.bibimbap.DailyPlanServer.domain.toDo.entity;

import com.bibimbap.DailyPlanServer.domain.category.entity.Category;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoResponseDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoUpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ToDo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "DAILYPLAN_ID")
    private DailyPlan dailyPlan;

    @Column(nullable = false)
    private String title;

    private Boolean isComplete;

    private String alarmStartTime;
    private String alarmEndTime;


    @Builder
    public ToDo(Category category, DailyPlan dailyPlan, String title, String alarmStartTime, String alarmEndTime){
        this.category = category;
        this.dailyPlan = dailyPlan;
        this.title = title;
        this.isComplete = false;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
    }

    public void update(ToDoUpdateDto toDoUpdateDto){
        this.title = toDoUpdateDto.getTitle();
        this.alarmStartTime = toDoUpdateDto.getAlarmStartTime();
        this.alarmEndTime = toDoUpdateDto.getAlarmEndTime();
        //this.category.setCategoryCode(toDoUpdateDto.getAfterCategoryCode());
    }
    public ToDoResponseDto toToDoResponseDto(){
        return ToDoResponseDto.builder()
                .entity(this)
                .build();
    }

}
