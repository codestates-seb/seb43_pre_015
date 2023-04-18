package com.pre015.server.member.service;

import com.pre015.server.member.entity.Member;
import com.pre015.server.member.entity.MemberStatus;
import com.pre015.server.member.exception.BusinessLogicException;
import com.pre015.server.member.exception.ExceptionCode;
import com.pre015.server.member.repository.MemberRepositoty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.validation.constraints.Positive;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service

public class MemberService {
    private final MemberRepositoty memberRepositoty;
    private final PasswordEncoder passwordEncoder;


    public Member saveMember(Member member){
        verifyExistEmail(member.getEmail());

        String encryptedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encryptedPassword);



        return memberRepositoty.save(member);
    }


    private void verifyExistEmail(String email){
        Optional<Member> member = memberRepositoty.findByEmail(email);
        if(member.isPresent())
            throw new BusinessLogicException(ExceptionCode.MEMBER_EXISTS);
    }

    @Transactional(readOnly = true)
    public Member findVerifiedMember(Long memberId) {
        Optional<Member> optionalUser = memberRepositoty.findById(memberId);
        Member findUser = optionalUser.orElseThrow(() -> new BusinessLogicException(ExceptionCode.MEMBER_NOT_FOUND));
        if (findUser.getMemberStatus() == MemberStatus.RESIGNED) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_RESIGNED);
        }
        return findUser;
    }


    // 회원탈퇴
    public void DeleteMember(long memberId) {

        Member member = findVerifiedMember(memberId);
        memberRepositoty.delete(member);
    }



    //회원 정보 수정
    @Transactional
    public Member updateMember(Member member) {
        Member findMember = findVerifiedMember(member.getId());

        Optional.ofNullable(member.getImg())
                .ifPresent(img -> findMember.setImg(img));
        Optional.ofNullable(member.getAbout())
                .ifPresent(about -> findMember.setAbout(about));
        Optional.ofNullable(member.getRoles())
                .ifPresent(roles -> findMember.setRoles(roles));

        return memberRepositoty.save(findMember);
    }
}



