package com.bibimbap.DailyPlanServer.domain.category.controller;

import com.bibimbap.DailyPlanServer.domain.category.dto.CategoryResponseDto;
import com.bibimbap.DailyPlanServer.domain.category.service.CategoryService;
import com.bibimbap.DailyPlanServer.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bibimbap.DailyPlanServer.global.result.ResultCode.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
//    @GetMapping("/list/{memberId}") // 회원의 카테고리 가져오기
//    public ResponseEntity<ResultResponse> findCategoryById(@PathVariable Long memberId){
//        CategoryResponseDto categoryResponseDto = categoryService.saveDailyPlan(member_id);
//        return ResponseEntity.ok(ResultResponse.of(GET_CATEGORY_LIST_SUCCESS, categoryResponseDto));
//    }
//
//    @GetMapping("/bestList/{memberId}") // 상위 달성률 카테고리 3개 가져오기
//    public ResponseEntity<ResultResponse> findCategoryById(@PathVariable Long memberId){
//        CategoryResponseDto categoryResponseDto = categoryService.saveDailyPlan(member_id);
//        return ResponseEntity.ok(ResultResponse.of(GET_BEST3_TODO_SUCCESS, categoryResponseDto));
//    }
//    @GetMapping("/worstList/{memberId}") // 하위 달성률 카테고리 3개 가져오기
//    public ResponseEntity<ResultResponse> findCategoryById(@PathVariable Long memberId){
//        CategoryResponseDto categoryResponseDto = categoryService.saveDailyPlan(member_id);
//        return ResponseEntity.ok(ResultResponse.of(GET_WORST3_TODO_SUCCESS, categoryResponseDto));
//    }
}
