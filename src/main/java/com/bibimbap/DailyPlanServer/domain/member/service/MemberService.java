package com.bibimbap.DailyPlanServer.domain.member.service;

import com.bibimbap.DailyPlanServer.domain.member.Member;
import com.bibimbap.DailyPlanServer.domain.member.MemberRepository;
import com.bibimbap.DailyPlanServer.domain.member.dto.MemberRequestDto;
import com.bibimbap.DailyPlanServer.domain.member.dto.MemberResponseDto;
import com.bibimbap.DailyPlanServer.global.error.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.bibimbap.DailyPlanServer.global.error.ErrorCode.MEMBER_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public MemberResponseDto saveOrUpdateMember(MemberRequestDto memberRequestDto){
        Member memberEntity = Member.builder()
                .email(memberRequestDto.getEmail())
                .name(memberRequestDto.getName())
                .profileUrl(memberRequestDto.getProfileUrl())
                .build();
        return memberRepository.save(memberEntity).toMemberResponseDto();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 id의 유저가 없습니다 : " + id)).toMemberResponseDto();
    }

    @Transactional(readOnly = true)
    public MemberResponseDto findMemberByEmail(String email) {
        return memberRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException(MEMBER_NOT_FOUND, "해당 이메일의 유저가 없습니다 : " + email)).toMemberResponseDto();
    }
}
