package com.pre015.server.answer.dto;

import com.pre015.server.answer.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class AnswerDTO {
    private Long answerId;
    private String content;
    private int likes;
    private boolean selectionStatus;
    private Long memberId;
    private Long questionId;

    public AnswerDTO(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.content = answer.getContent();
        this.likes = answer.getLikes();
        this.selectionStatus = answer.isSelectionStatus();
        this.memberId = answer.getMember().getMemberId();
        this.questionId = answer.getQuestion().getQuestionId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerDTO)) return false;
        AnswerDTO answerDTO = (AnswerDTO) o;
        return answerId != null && Objects.equals(answerId, answerDTO.answerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId);
    }
}
