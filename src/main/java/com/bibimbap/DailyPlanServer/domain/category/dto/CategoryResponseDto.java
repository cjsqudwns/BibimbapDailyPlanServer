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
    private Member member;
    private CategoryCode categoryCode;
    private Long countByToDo;
    private Long successToDoCount;

    @Builder
    public CategoryResponseDto(Category entity){
        this.id = entity.getId();
        this.member = entity.getMember();
        this.categoryCode = entity.getCategoryCode();
        this.countByToDo = entity.getCountByToDo();
        this.successToDoCount = entity.getSuccessToDoCount();
    }
}
