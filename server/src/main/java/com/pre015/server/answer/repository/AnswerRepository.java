package com.pre015.server.answer.repository;

import com.pre015.server.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
//    List<Answer> findByMemberId(Long memberId);
//    List<Answer> findByQuestionId(Long questionId);
}
