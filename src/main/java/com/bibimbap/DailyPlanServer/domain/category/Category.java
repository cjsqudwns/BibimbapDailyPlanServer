package com.bibimbap.DailyPlanServer.domain.category;

import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import com.bibimbap.DailyPlanServer.domain.member.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "DAILY_PLAN_ID")
    private DailyPlan dailyPlan;

    @Builder
    public Category(Member member, DailyPlan dailyPlan){
        this.member = member;
        this.dailyPlan = dailyPlan;
    }
}
