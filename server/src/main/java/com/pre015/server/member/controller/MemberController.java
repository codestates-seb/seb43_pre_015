package com.pre015.server.member.controller;

import com.pre015.server.member.dto.MemberDto;
import com.pre015.server.member.dto.SingleResponseDto;
import com.pre015.server.member.entity.Member;
import com.pre015.server.member.mapper.MemberMapper;
import com.pre015.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
@Validated
public class MemberController {
    private final MemberService memberService;
    private final MemberMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MemberDto.Response postMember(@Valid @RequestBody MemberDto.Post requestDto) {
        Member member = mapper.postDtoToEntity(requestDto);
        Member createdMember = memberService.saveMember(member);
        return mapper.EntityToResPonseDto(createdMember);
    }

    @GetMapping("/{member_id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberDto.Response getMember(
            @PathVariable("member_id") @Positive Long memberId) {
        Member member = memberService.findVerifiedMember(memberId);
        return mapper.EntityToResPonseDto(member);
    }

//    @GetMapping("/{member_id}")
//    @ResponseStatus(HttpStatus.OK)
//    public MemberDto.Response getProfile(@PathVariable("member_id")@Positive Long memberId) {
//        return mapper.EntityToResPonseDto(memberService.findVerifiedMember(memberId));
//    }

    @DeleteMapping("/{userId}")   // 회원탈퇴
    public ResponseEntity deleteMember(
            @PathVariable("memberId") @Positive long memberId){

        memberService.deleteMember(memberId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }


    //회원 정보 수정
    @PatchMapping("{member_id}")
    public ResponseEntity patchMember(@Valid @RequestBody MemberDto.Patch requestBody,
                                      @PathVariable("member_id") @Positive Long memberId) {

        requestBody.setMemberId(memberId);

        Member member =
                memberService.updateMember(mapper.memberPatchToMember(requestBody));

        return new ResponseEntity<>(new SingleResponseDto<>(mapper.memberToMemberInfoResponse(member)), HttpStatus.OK);
    }
}

