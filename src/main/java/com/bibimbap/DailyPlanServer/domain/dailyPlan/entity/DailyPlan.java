package com.bibimbap.DailyPlanServer.domain.dailyPlan.entity;

import com.bibimbap.DailyPlanServer.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import com.bibimbap.DailyPlanServer.domain.toDo.entity.ToDo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class DailyPlan {
    @Id
    @Column(name = "DAILYPLAN_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @Column(nullable = false)
    private String yearMonth;

    @Column(nullable = false)
    private int day;

    @OneToMany(mappedBy = "dailyPlan")
    List<ToDo> toDos = new ArrayList<>();

    @Builder
    public DailyPlan(Member member,String yearMonth, int day){
        this.member = member;
        this.yearMonth = yearMonth;
        this.day = day;
    }
}
