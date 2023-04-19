package com.pre015.server.comment.dto;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;


public class CommentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @Positive
        private Long memberId;

        @Positive
        private Long answerId;

        @NotBlank(message = "content name should not be blank")
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        @Positive
        private Long memberId;

        @Positive
        private Long answerId;

        @Setter
        private Long commentId;

        @NotBlank(message = "content name should not be blank")
        private String content;
    }

    @Setter
    @AllArgsConstructor
    public static class Response {

        private Long commentId;
        private String content;
        private Member member;
        private Answer answer;


    }
}