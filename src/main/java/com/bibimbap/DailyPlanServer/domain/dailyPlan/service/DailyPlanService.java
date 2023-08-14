package com.bibimbap.DailyPlanServer.domain.dailyPlan.service;

import com.bibimbap.DailyPlanServer.domain.dailyPlan.dto.DailyPlanResponseDto;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlan;
import com.bibimbap.DailyPlanServer.domain.dailyPlan.entity.DailyPlanRepository;
import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import com.bibimbap.DailyPlanServer.domain.member.entity.MemberRepository;
import com.bibimbap.DailyPlanServer.global.error.ErrorCode;
import com.bibimbap.DailyPlanServer.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.*;

@RequiredArgsConstructor
@Service
public class DailyPlanService {
    private final MemberRepository memberRepository;
    private final DailyPlanRepository dailyPlanRepository;

    private final int YEAR_MONTH_START_INDEX = 0;
    private final int DAY_START_INDEX = 6;
    private final int DAY_END_INDEX = 8;
    @Transactional
    public Long saveDailyPlan(Long memberId, String yearMonthDay){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));
        String yearMonth = yearMonthDay.substring(YEAR_MONTH_START_INDEX, DAY_START_INDEX);
        int day = Integer.parseInt(yearMonthDay.substring(DAY_START_INDEX, DAY_END_INDEX));
        DailyPlan dailyPlan = DailyPlan.builder()
                .member(member)
                .yearMonth(yearMonth)
                .day(day)
                .build();
        return dailyPlanRepository.save(dailyPlan).getId();
    }

    @Transactional(readOnly = true)
    public DailyPlanResponseDto findById(Long dailyPlanId){
        return dailyPlanRepository.findById(dailyPlanId)
                .map(DailyPlanResponseDto::new)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 데일리 플랜이 존재하지 않습니다  : " + dailyPlanId));
    }

    @Transactional
    public boolean deleteDailyPlan(Long dailyPlanId){
        DailyPlan dailyPlan = dailyPlanRepository.findById(dailyPlanId)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND,  "해당 데일리 플랜이 존재하지 않아 삭제할 수 없습니다  : " + dailyPlanId));
        dailyPlanRepository.delete(dailyPlan);
        return true;
    }

    @Transactional(readOnly = true)
    public List<DailyPlanResponseDto> getMonthDailyPlanList(Long memberId, String yearMonth){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 멤버가 존재하지 않습니다 : " + memberId));
        List<DailyPlanResponseDto> dailyPlanResponseDtoList = dailyPlanRepository.findByYearMonth(yearMonth)
                .stream()
                .map(DailyPlanResponseDto::new)
                .collect(Collectors.toList());
        if(dailyPlanResponseDtoList.size() == 0)
            throw new EntityNotFoundException(DAILYPLAN_MONTHLIST_NOT_FOUND, "해당 월에 작성된 데일리 플래너가 없습니다 : " + yearMonth);

        return dailyPlanResponseDtoList;
    }
}
