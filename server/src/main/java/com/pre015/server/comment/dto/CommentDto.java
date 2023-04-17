package com.pre015.server.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;


public class CommentDto {

    @Getter
    @AllArgsConstructor
    public static class Post {

        @NotBlank(message = "content name should not be blank")
        private String content;
    }

    @Getter
    @AllArgsConstructor
    public static class Patch {

        @NotBlank(message = "댓글 id를 입력해주세요")
        private Long contentId;

        @NotBlank(message = "content name should not be blank")
        private String content;
    }
}
