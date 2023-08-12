package com.bibimbap.DailyPlanServer.domain.category.entity;

import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
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

    private CategoryCode categoryCode;
    private Long countByToDo;
    private Long successToDoCount;

    @Builder
    public Category(Member member, CategoryCode categoryCode){
        this.member = member;
        this.categoryCode = CategoryCode.DAILY;
        this.countByToDo = 0L;
        this.successToDoCount = 0L;
    }

    public void setCategoryCode(CategoryCode categoryCode) {
        this.categoryCode = categoryCode;
    }
}
