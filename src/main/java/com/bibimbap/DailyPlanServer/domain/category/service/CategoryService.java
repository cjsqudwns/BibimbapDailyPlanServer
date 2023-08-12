package com.bibimbap.DailyPlanServer.domain.category.service;

import com.bibimbap.DailyPlanServer.domain.category.entity.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

//    @Transactional(readOnly = true)
//    public CategoryResponseDto findCategoryById(Long memberId){
//        List<Category> category = categoryRepository.findByMemberId(memberId);
//
//    }
}
