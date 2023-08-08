package com.bibimbap.DailyPlanServer.domain.toDoList;

import com.bibimbap.DailyPlanServer.domain.comment.Comment;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class ToDoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "DAILYPLAN_ID")
    private DailyPlan dailyPlan;

    @OneToOne
    @JoinColumn(name = "COMMENT_ID")
    private Comment comment;

    @Column(nullable = false)
    private String title;

    private Boolean isComplete;

    private String alarmTime;

    @Builder
    public ToDoList(DailyPlan dailyPlan, String title, String alarmTime){
        this.dailyPlan = dailyPlan;
        this.title = title;
        this.alarmTime = alarmTime;
        this.isComplete = true;
    }
}
