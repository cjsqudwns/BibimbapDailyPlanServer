package com.bibimbap.DailyPlanServer.domain.category.controller;

import com.bibimbap.DailyPlanServer.domain.category.dto.CategoryResponseDto;
import com.bibimbap.DailyPlanServer.domain.category.service.CategoryService;
import com.bibimbap.DailyPlanServer.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bibimbap.DailyPlanServer.global.result.ResultCode.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/list/{memberId}") // 회원의 카테고리 가져오기
    public ResponseEntity<ResultResponse> getCategoryList(@PathVariable Long memberId){
        List<CategoryResponseDto> categoryResponseDtos= categoryService.getCategoryListByMemberId(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_CATEGORY_LIST_SUCCESS, categoryResponseDtos));
    }

    @GetMapping("/best3/{memberId}") // 상위 달성률 카테고리 3개 가져오기
    public ResponseEntity<ResultResponse> getBest3Category(@PathVariable Long memberId){
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getBest3DailyPlanCategory(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_BEST3_TODO_SUCCESS, categoryResponseDtos));
    }
    @GetMapping("/worst3/{memberId}") // 하위 달성률 카테고리 3개 가져오기
    public ResponseEntity<ResultResponse> getWorst3Category(@PathVariable Long memberId){
        List<CategoryResponseDto> categoryResponseDtos = categoryService.getWorst3DailyPlanCategory(memberId);
        return ResponseEntity.ok(ResultResponse.of(GET_WORST3_TODO_SUCCESS, categoryResponseDtos));
    }
}
