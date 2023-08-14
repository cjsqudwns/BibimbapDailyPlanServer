package com.bibimbap.DailyPlanServer.domain.dailyPlan.controller;

import com.bibimbap.DailyPlanServer.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.service.DailyPlanService;
import com.bibimbap.DailyPlanServer.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.bibimbap.DailyPlanServer.global.result.ResultCode.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dailyplan")
public class DailyPlanController {
    private final DailyPlanService dailyPlanService;

    @PostMapping("/{memberId}")
    public ResponseEntity<ResultResponse> saveDailyPlan(@PathVariable Long memberId, @RequestParam("date") String date){
        Long saveId = dailyPlanService.saveDailyPlan(memberId, date);
        return ResponseEntity.ok(ResultResponse.of(DAILYPLAN_SAVE_SUCCESS, saveId));
    }

    @GetMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> getDailyPlan(@PathVariable Long dailyPlanId){
        DailyPlanResponseDto dailyPlanResponseDto = dailyPlanService.findById(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(GET_DAILYPLAN_SUCCESS, dailyPlanResponseDto));
    }

    @DeleteMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> deleteDailyPlan(@PathVariable Long dailyPlanId){
        return ResponseEntity.ok(ResultResponse.of(DELETE_DAILPLAN_SUCCESS, dailyPlanService.deleteDailyPlan(dailyPlanId)));
    }

    @GetMapping("/{memberId}/{yearMonth}")
    public ResponseEntity<ResultResponse> getMonthDailyPlanList(@PathVariable Long member_id, @PathVariable String yearMonth){
        List<DailyPlanResponseDto> monthDailyPlanListByMemberId = dailyPlanService.getMonthDailyPlanList(member_id, yearMonth);
        return ResponseEntity.ok(ResultResponse.of(GET_MONTH_DAILYPLAN_SUCCESS, monthDailyPlanListByMemberId));
    }


}
