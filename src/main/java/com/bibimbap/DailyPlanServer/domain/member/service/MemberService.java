package com.bibimbap.DailyPlanServer.domain.member.service;

import com.bibimbap.DailyPlanServer.domain.member.entity.Member;
import com.bibimbap.DailyPlanServer.domain.member.entity.MemberRepository;
import com.bibimbap.DailyPlanServer.domain.member.dto.MemberRequestDto;
import com.bibimbap.DailyPlanServer.domain.member.dto.MemberResponseDto;
import com.bibimbap.DailyPlanServer.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.MEMBER_NOT_FOUND;
import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.USERNAME_ALREADY_EXIST;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto saveOrUpdateMember(MemberRequestDto memberRequestDto){
        Member member = memberRepository.findByEmail(memberRequestDto.getEmail())
                .map(entity -> entity.update(memberRequestDto.getName(), memberRequestDto.getProfileUrl()))
                .orElse(memberRequestDto.toEntity());
        return MemberResponseDto.builder()
                .entity(memberRepository.save(member))
                .build();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMemberById(Long id) {
        return memberRepository.findById(id)
                .map(MemberResponseDto::new)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 id의 유저가 없습니다 : " + id));
    }
}
