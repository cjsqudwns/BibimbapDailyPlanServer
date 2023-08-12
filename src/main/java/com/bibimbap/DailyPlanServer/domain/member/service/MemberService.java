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
        Optional<Member> byName = memberRepository.findByName(memberRequestDto.getName());
        if(byName.isPresent())
            throw new EntityNotFoundException(USERNAME_ALREADY_EXIST, "이미 존재하는 사용자 이름입니다 : " + memberRequestDto.getName());
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
