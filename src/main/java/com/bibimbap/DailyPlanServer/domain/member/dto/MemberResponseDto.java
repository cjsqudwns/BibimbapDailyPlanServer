package com.bibimbap.DailyPlanServer.domain.member.dto;

import com.bibimbap.DailyPlanServer.domain.member.Member;
import jakarta.websocket.server.ServerEndpoint;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponseDto {
    private Long id;
    private String name;
    private String email;
    private String profileUrl;
    private Long planSuccessCount;
    private Long challengeSuccessCount;
    @Builder
    public MemberResponseDto(Member entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.profileUrl = entity.getProfileUrl();
        this.planSuccessCount = entity.getPlanSuccessCount();
        this.challengeSuccessCount = entity.getChallengeSuccessCount();
    }
}