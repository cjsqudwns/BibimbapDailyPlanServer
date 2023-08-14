package com.bibimbap.DailyPlanServer.domain.member.entity;

import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import com.bibimbap.DailyPlanServer.domain.member.dto.MemberResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "MEMBER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private String profileUrl;

    private int planSuccessCount;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<DailyPlan> dailyPlans = new ArrayList<>();

    @Builder
    public Member(String name, String email, String profileUrl, int planSuccessCount){
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
        this.planSuccessCount = planSuccessCount;
    }

    public Member update(String name, String picture){
        this.name = name;
        this.profileUrl = picture;
        return this;
    }
}
