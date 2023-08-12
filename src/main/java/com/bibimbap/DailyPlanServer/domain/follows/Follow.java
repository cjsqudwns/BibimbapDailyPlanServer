package com.bibimbap.DailyPlanServer.domain.follows;

import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "FOLLOW_MEMBER_ID")
    private Member followMember;

    @Builder
    public Follow(Member member, Member followMember){
        this.member = member;
        this.followMember = followMember;
    }

}
