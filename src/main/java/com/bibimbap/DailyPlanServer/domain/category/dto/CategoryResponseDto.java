package com.bibimbap.DailyPlanServer.domain.category.dto;

import com.bibimbap.DailyPlanServer.domain.category.entity.Category;
import com.bibimbap.DailyPlanServer.domain.category.entity.CategoryCode;
import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryResponseDto {
    private Long id;
    private String title;
    private String code;
    private int countByDailyPlan;
    private int countByChallenge;
    private int successDailyPlanCount;
    private int successChallengeCount;

    @Builder
    public CategoryResponseDto(Category entity){
        this.id = entity.getId();
        this.title = entity.getCategoryCode().getTitle();
        this.code = entity.getCategoryCode().getCode();
        this.countByDailyPlan = entity.getCountByToDo();
        //this.countByChallenge = entity.getCountByChallenge();
        this.successDailyPlanCount = entity.getSuccessToDoCount();
        //this.successChallengeCount = entity.getSuccessChallengeCount();
    }
}
