package com.bibimbap.DailyPlanServer.domain.category.service;

import com.bibimbap.DailyPlanServer.domain.category.dto.CategoryResponseDto;
import com.bibimbap.DailyPlanServer.domain.category.entity.Category;
import com.bibimbap.DailyPlanServer.domain.category.entity.CategoryRepository;
import com.bibimbap.DailyPlanServer.domain.member.entity.MemberRepository;
import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import com.bibimbap.DailyPlanServer.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getCategoryListByMemberId(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "카테고리에 연결된 해당 멤버가 존재하지 않습니다 : " + memberId));

        List<Category> categoryList = categoryRepository.findAllByMemberId(memberId);
        if(categoryList.size() == 0)
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND, "해당 유저는 데일리플랜을 작성하지 않아 카테고리가 없습니다");
        return categoryList.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public List<CategoryResponseDto> getBest3DailyPlanCategory(Long memberId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "카테고리에 연결된 해당 멤버가 존재하지 않습니다 : " + memberId));

        List<Category> categoryList = categoryRepository.findAllByMemberId(memberId);
        categoryList.sort((x,y) -> Double.compare(computeDaily));
        if(categoryList.size() == 0)
            throw new EntityNotFoundException(CATEGORY_NOT_FOUND, "해당 유저는 데일리플랜을 작성하지 않아 카테고리가 없습니다");
        return categoryList.stream()
                .map(CategoryResponseDto::new)
                .collect(Collectors.toList());
    }

}
