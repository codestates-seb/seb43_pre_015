package com.pre015.server.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerPostDTO {
    private String content;
    private Long memberId;
    private Long questionId;

    public AnswerPostDTO(String content, Long memberId, Long questionId) {
        this.content = content;
        this.memberId = memberId;
        this.questionId = questionId;
    }
}
