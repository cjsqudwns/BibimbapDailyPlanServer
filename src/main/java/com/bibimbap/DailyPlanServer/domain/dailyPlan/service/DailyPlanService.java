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

import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.DAILYPLAN_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DailyPlanService {
    private final MemberRepository memberRepository;
    private final DailyPlanRepository dailyPlanRepository;

    @Transactional
    public DailyPlanResponseDto saveDailyPlan(Long memberId, String date){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException(ErrorCode.MEMBER_NOT_FOUND, "해당 id의 유저가 없습니다 : " + memberId));
        String yearMonth = date.substring(0, 6);
        String day = date.substring(7);
        DailyPlan dailyPlanEntity = DailyPlan.builder()
                .member(member)
                .yearMonth(yearMonth)
                .date(day)
                .build();
        return dailyPlanRepository.save(dailyPlanEntity).toDailyPlanResponseDto();
    }

    @Transactional(readOnly = true)
    public DailyPlanResponseDto findById(Long id){
        return dailyPlanRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(DAILYPLAN_NOT_FOUND, "해당 id의 데일리 플랜이 없습니다 : " + id)).toDailyPlanResponseDto();
    }

    @Transactional
    public boolean deleteDailyPlan(Long id){
        Optional<DailyPlan> byId = dailyPlanRepository.findById(id);
        if(!byId.isPresent())
            throw new EntityNotFoundException(DAILYPLAN_NOT_FOUND,"해당 id의 데일리 플랜이 없습니다 : " + id);
        dailyPlanRepository.deleteById(id);
        return true;
    }

    @Transactional(readOnly = true)
    public List<DailyPlanResponseDto> findMonth(Long id){
        Optional<DailyPlan> byId = dailyPlanRepository.findById(id);
        if(!byId.isPresent())
            throw new EntityNotFoundException(DAILYPLAN_NOT_FOUND,"해당 id의 데일리 플랜이 없습니다 : " + id);
        List<DailyPlan> monthlyData = dailyPlanRepository.findByYearMonth("202308");
        return monthlyData.stream() // 그냥 여기서 db접근말고 filter를 써도 되는지?
                .map(entity -> {
                    return DailyPlanResponseDto.builder()
                            .entity(entity)
                            .build();
                })
                .collect(Collectors.toList());
    }
}
