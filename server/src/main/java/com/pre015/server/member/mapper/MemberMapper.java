package com.pre015.server.member.mapper;

import com.pre015.server.member.dto.MemberDto;
import com.pre015.server.member.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {

    Member postDtoToEntity(MemberDto.Post postDto);

    MemberDto.Response EntityToResPonseDto(Member member);


    Member memberPatchToMember(MemberDto.Patch requestBody);
    MemberDto.MyPageResponse memberToMemberInfoResponse(Member member);


}
