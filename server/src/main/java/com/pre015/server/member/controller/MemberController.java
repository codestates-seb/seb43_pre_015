package com.pre015.server.member.controller;

import com.pre015.server.member.dto.MemberDto;
import com.pre015.server.member.entity.Member;
import com.pre015.server.member.jwt.JwtTokenizer;
import com.pre015.server.member.mapper.MemberMapper;
import com.pre015.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final JwtTokenizer jwtTokenizer;

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

    @GetMapping("/profile")
    @ResponseStatus(HttpStatus.OK)
    public MemberDto.Response getProfile(@RequestHeader(name = "Authorization") String token) {
        return mapper.EntityToResPonseDto(memberService.findVerifiedMember(jwtTokenizer.getMemberId(token)));

    }
}

