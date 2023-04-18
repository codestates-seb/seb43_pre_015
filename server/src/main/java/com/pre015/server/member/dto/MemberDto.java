package com.pre015.server.member.dto;

import com.pre015.server.member.entity.MemberStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class MemberDto {
    @Builder

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
    @Builder
    public static class Post {
        @Email(message = "Email should not be empty.")
        private String email;

        @Pattern(regexp = "^[A-Za-z\\d!@#$%^&*()_+~\\-=]{8,40}$")
        private String password;

        @NotBlank
        private String displayName;
    }

}
