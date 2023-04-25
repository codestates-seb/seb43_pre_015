package com.pre015.server.member.dto;

import com.pre015.server.member.entity.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.*;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class MemberDto {

    @Getter
    @Setter
    public static class Response {
        private Long memberId;
        private String displayName;
        private String email;
        private MemberStatus memberStatus;
        private LocalDateTime createdDate;
        private LocalDateTime last_modified_at;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post {

        @Email(message = "Email should not be empty.")
        private String email;

        @Pattern(regexp = "^[A-Za-z\\d!@#$%^&*()_+~\\-=]{8,40}$")
        private String password;

        @NotBlank
        private String displayName;
    }

    @Getter
    @Setter
    @AllArgsConstructor // 테스트
    public static class Patch {

        private Long memberId;
        private String img;
        private String about;

    }

    @AllArgsConstructor
    @Getter
    public static class MyPageResponse {
        private Long memberId;
        private String email;

        @Setter
        private Integer answerCount;
    }

}
