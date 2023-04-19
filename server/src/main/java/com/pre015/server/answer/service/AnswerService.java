package com.pre015.server.answer.service;

import com.pre015.server.answer.dto.AnswerDTO;

import java.util.List;

public interface AnswerService {
    AnswerDTO createAnswer(AnswerDTO answerDTO);
    AnswerDTO updateAnswer(Long id, AnswerDTO answerDTO);
    AnswerDTO findAnswer(Long answerId);
    void deleteAnswer(Long id);
    void acceptAnswer(Long questionId, Long answerId);
    List<AnswerDTO> findAllAnswers();
    List<AnswerDTO> findAnswersByMember(Long memberId);
    List<AnswerDTO> findAnswersByQuestion(Long questionId);

}
