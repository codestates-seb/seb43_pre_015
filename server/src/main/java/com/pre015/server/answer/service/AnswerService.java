package com.pre015.server.answer.service;

import com.pre015.server.answer.dto.AnswerDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnswerService {
    AnswerDTO createAnswer(AnswerDTO answerDTO);
    AnswerDTO updateAnswer(Long id, AnswerDTO answerDTO);
    void deleteAnswer(Long id);
    List<AnswerDTO> findAllAnswers();
    AnswerDTO findAnswerById(Long answerId);
//    List<AnswerDTO> findAnswersByMemberId(Long memberId);
//    List<AnswerDTO> findAnswersByQuestionId(Long questionId);
    void acceptAnswer(Long questionId, Long answerId);
}
