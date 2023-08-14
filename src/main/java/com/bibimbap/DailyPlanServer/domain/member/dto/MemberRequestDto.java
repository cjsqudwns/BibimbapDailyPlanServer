package com.bibimbap.DailyPlanServer.domain.member.dto;

import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberRequestDto {
    private String name;
    private String email;
    private String profileUrl;

    public Member toEntity(){
        return Member.builder()
                .name(this.name)
                .email(this.email)
                .profileUrl(this.profileUrl)
                .build();
    }
}
