package com.pre015.server.answer.repository;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.member.entity.Member;
import com.pre015.server.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findByMember(Member member);

    List<Answer> findByQuestion(Question question);
}