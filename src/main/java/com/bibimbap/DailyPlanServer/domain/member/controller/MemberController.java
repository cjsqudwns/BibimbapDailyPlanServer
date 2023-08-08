package com.bibimbap.DailyPlanServer.domain.member.controller;

import com.bibimbap.DailyPlanServer.domain.member.dto.MemberRequestDto;
import com.bibimbap.DailyPlanServer.domain.member.dto.MemberResponseDto;
import com.bibimbap.DailyPlanServer.domain.member.service.MemberService;
import com.bibimbap.DailyPlanServer.global.result.ResultCode;
import com.bibimbap.DailyPlanServer.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.bibimbap.DailyPlanServer.global.result.ResultCode.GET_USERPROFILE_SUCCESS;
import static com.bibimbap.DailyPlanServer.global.result.ResultCode.SAVE_OR_UPDATE_SUCCESS;

@RequiredArgsConstructor
@RestController
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;
    @PostMapping("") // 하나의 함수에서 save랑 동시에 update하는 방법?
    public ResponseEntity<ResultResponse> saveOrUpdateMember(@RequestBody MemberRequestDto memberRequestDto){
        MemberResponseDto memberResponseDto = memberService.saveOrUpdateMember(memberRequestDto);
        return ResponseEntity.ok(ResultResponse.of(SAVE_OR_UPDATE_SUCCESS, memberResponseDto));
    }
    @GetMapping("/findById/{member_id}") // 멤버 아이디와 이메일 앞에 주소를 추가하지 않았을 때 서버 오류 생김:ambiguous
    public ResponseEntity<ResultResponse> findMemberById(@PathVariable Long member_id){
        MemberResponseDto memberResponseDto = memberService.findMemberById(member_id);
        return ResponseEntity.ok(ResultResponse.of(GET_USERPROFILE_SUCCESS, memberResponseDto));
    }
    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<ResultResponse> findMemberByEmail(@PathVariable String email){
        MemberResponseDto memberResponseDto = memberService.findMemberByEmail(email);
        return ResponseEntity.ok(ResultResponse.of(GET_USERPROFILE_SUCCESS, memberResponseDto));
    }
}
