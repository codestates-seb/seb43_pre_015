package com.pre015.server.question.dto;

import com.pre015.server.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;
import java.util.List;

public class QuestionDto {

    @Getter
    @AllArgsConstructor
    public static class Post {
        @NotBlank
        private String title;
        @NotBlank
        private String content;
        @Positive
        private long memberId;

        public Member getMember() {
            Member member = new Member();
            member.setMemberId(memberId);

            return member;
        }
    }
    @Getter
    @Setter
    public static class Patch {
        private long questionId;
        @NotNull
        private String title;
        @NotNull
        private String content;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private long questionId;
        private String title;
        private String content;
        private long memberId;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
        private long answerCount;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DetailsResponse {
        private long questionId;
        private String title;
        private String content;
        private long memberId;
        private LocalDateTime createdAt;
        private LocalDateTime modifiedAt;
    }

    @Getter
    @AllArgsConstructor
    public static class PageInfo {
        private int page;
        private int size;
        private long totalElements;
        private int totalPages;
    }

    @Getter
    @AllArgsConstructor
    public static class MultiResponse<T> {
        private List<T> data;
        private PageInfo pageInfo;
    }
}
