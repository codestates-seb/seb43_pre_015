package com.pre015.server.member.service;

import com.pre015.server.member.entity.Member;
import com.pre015.server.member.entity.MemberStatus;
import com.pre015.server.exception.BusinessLogicException;
import com.pre015.server.exception.ExceptionCode;
import com.pre015.server.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member saveMember(Member member){
        verifyExistEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);

        return memberRepository.save(member);
    }

    private void verifyExistEmail(String email){
        Optional<Member> member = memberRepository.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(Long memberId) {
        Optional<Member> optionalUser = memberRepository.findById(memberId);
        Member findUser = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        if (findUser.getMemberStatus() == MemberStatus.RESIGNED) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_RESIGNED);
        }
        return findUser;
    }

    // 회원탈퇴
    public void deleteMember(long memberId) {

        Member member = findVerifiedMember(memberId);
        memberRepository.delete(member);
    }


    //회원 정보 수정
    @Transactional
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getMemberId());

        Optional.ofNullable(member.getRoles())
                .ifPresent(roles -> findMember.setRoles(roles));

        return memberRepository.save(findMember);
    }
}



