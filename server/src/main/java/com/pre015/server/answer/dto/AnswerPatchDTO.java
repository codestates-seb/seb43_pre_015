package com.pre015.server.answer.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AnswerPatchDTO {
    private String content;

    public AnswerPatchDTO(String content) {
        this.content = content;
    }
}
