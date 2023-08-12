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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    private String yearMonth;
    private String date;

    @OneToMany(mappedBy = "dailyPlan")
    List<ToDo> toDo = new ArrayList<>();

    @Builder
    public DailyPlan(Member member,String yearMonth, String date){
        this.member = member;
        this.yearMonth = yearMonth;
        this.date = date;
    }

    public DailyPlanResponseDto toDailyPlanResponseDto(){

        return DailyPlanResponseDto.builder()
                .entity(this)
                .build();
    }
}
