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

    @PostMapping("/{member_id}/{date}")
    public ResponseEntity<ResultResponse> saveDailyPlan(@PathVariable Long member_id, @PathVariable String date){
        DailyPlanResponseDto dailyPlanResponseDto = dailyPlanService.saveDailyPlan(member_id, date);
        return ResponseEntity.ok(ResultResponse.of(DAILYPLAN_SAVE_SUCCESS, dailyPlanResponseDto));
    }

    @GetMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> findById(@PathVariable Long dailyPlanId){
        DailyPlanResponseDto dailyPlanResponseDto = dailyPlanService.findById(dailyPlanId);
        return ResponseEntity.ok(ResultResponse.of(GET_DAILYPLAN_SUCCESS, dailyPlanResponseDto));
    }

    @DeleteMapping("/{dailyPlanId}")
    public ResponseEntity<ResultResponse> deleteDailyPlan(@PathVariable Long dailyPlanId){
        return ResponseEntity.ok(ResultResponse.of(DELETE_DAILPLAN_SUCCESS, dailyPlanService.deleteDailyPlan(dailyPlanId)));
    }

    @GetMapping("/{member_id}/month")
    public ResponseEntity<ResultResponse> findMonthById(@PathVariable Long member_id){
        List<DailyPlanResponseDto> dailyPlanResponseDtoList = dailyPlanService.findMonth(member_id);
        return ResponseEntity.ok(ResultResponse.of(GET_DAILYPLAN_SUCCESS, dailyPlanResponseDtoList));
    }


}
