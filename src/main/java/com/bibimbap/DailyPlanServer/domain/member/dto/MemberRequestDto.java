package com.bibimbap.DailyPlanServer.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
    private String profileUrl;
    @Builder
    public MemberRequestDto(String name, String email, String profileUrl){
        this.name = name;
        this.email = email;
        this.profileUrl = profileUrl;
    }
}
