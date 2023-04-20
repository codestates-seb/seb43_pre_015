package com.pre015.server.answer.dto;

import com.pre015.server.answer.entity.Answer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AnswerResponseDTO {
    private Long answerId;
    private String content;
    private int likes;
    private boolean selectionStatus;
    private Long memberId;
    private String memberDisplayName;
    private Long questionId;
    private LocalDateTime createdTime;
    private LocalDateTime modifiedTime;

    public AnswerResponseDTO(Answer answer) {
        this.answerId = answer.getAnswerId();
        this.content = answer.getContent();
        this.likes = answer.getLikes();
        this.selectionStatus = answer.isSelectionStatus();
        this.memberId = answer.getMember().getMemberId();
        this.memberDisplayName = answer.getMember().getDisplayName();
        this.questionId = answer.getQuestion().getQuestionId();
        this.createdTime = answer.getCreatedTime();
        this.modifiedTime = answer.getModifiedTime();
    }
}
