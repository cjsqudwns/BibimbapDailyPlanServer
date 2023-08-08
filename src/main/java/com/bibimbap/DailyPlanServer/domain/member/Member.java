package com.bibimbap.DailyPlanServer.domain.member;

import com.bibimbap.DailyPlanServer.domain.member.dto.MemberResponseDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String profileUrl;

    private Long planSuccessCount;
    private Long challengeSuccessCount;

    @Builder
    public Member(String name, String email, String profileUrl){
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
        this.planSuccessCount = 0L;
        this.challengeSuccessCount = 0L;
    }
    public MemberResponseDto toMemberResponseDto(){
        return MemberResponseDto.builder()
                .entity(this)
                .build();
    }
}
