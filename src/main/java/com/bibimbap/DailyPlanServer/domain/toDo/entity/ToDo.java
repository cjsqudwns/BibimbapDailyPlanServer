package com.bibimbap.DailyPlanServer.domain.toDo.entity;

import com.bibimbap.DailyPlanServer.domain.BaseTimeEntity;
import com.bibimbap.DailyPlanServer.domain.category.entity.Category;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.ToDoStatus;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoRequestDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoResponseDto;
import com.bibimbap.DailyPlanServer.domain.toDo.dto.ToDoUpdateDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ToDo extends BaseTimeEntity {

    @Id
    @Column(name = "TODO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    private ToDoStatus toDoStatus;

    private String alarmStartTime;
    private String alarmEndTime;

    @ManyToOne
    @JoinColumn(name = "DAILYPLAN_ID")
    private DailyPlan dailyPlan;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @Builder
    public ToDo( String title, String alarmStartTime, String alarmEndTime, DailyPlan dailyPlan, Category category){
        this.title = title;
        this.toDoStatus = ToDoStatus.IN_PROGRESS;
        this.alarmStartTime = alarmStartTime;
        this.alarmEndTime = alarmEndTime;
        this.dailyPlan = dailyPlan;
        this.category = category;
    }

    public ToDo update(ToDoRequestDto dto){
        this.title = dto.getTitle();
        this.alarmStartTime = dto.getAlarmStartTime();
        this.alarmEndTime = dto.getAlarmEndTime();
        return this;
    }
    public void setToDoStatus(ToDoStatus toDoStatus){
        this.toDoStatus = toDoStatus;
    }

    public void setCategory(Category category){
        this.category = category;
    }

    public void setDailyPlan(DailyPlan dailyPlan){
        this.dailyPlan = dailyPlan;
    }

}
