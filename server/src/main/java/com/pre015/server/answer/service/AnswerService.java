package com.pre015.server.answer.service;

import com.pre015.server.answer.dto.AnswerPostDTO;
import com.pre015.server.answer.dto.AnswerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnswerService {
    AnswerResponseDTO createAnswer(AnswerPostDTO answerPostDTO);
    AnswerResponseDTO updateAnswer(Long id, String content);
    AnswerResponseDTO findAnswer(Long answerId);
    void deleteAnswer(Long id);
    void acceptAnswer(Long questionId, Long answerId);
    Page<AnswerResponseDTO> findAllAnswers(int page, int size);
    Page<AnswerResponseDTO> findAnswersByMember(Long memberId, int page, int size);
    Page<AnswerResponseDTO> findAnswersByQuestion(Long questionId, int page, int size);
}
