package com.pre015.server.answer.dto;

import com.pre015.server.answer.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDTO {
    private Long id;
    private String content;
    private int like;
    private boolean selectionStatus;
    private Long memberId;
    private Long questionId;

    public AnswerDTO(Answer answer) {
        this.id = answer.getId();
        this.content = answer.getContent();
        this.like = answer.getLike();
        this.selectionStatus = answer.isSelectionStatus();
        this.memberId = answer.getMember().getMemberId();
        this.questionId = answer.getQuestion().getId();
    }
}
