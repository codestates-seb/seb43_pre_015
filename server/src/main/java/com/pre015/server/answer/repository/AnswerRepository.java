package com.pre015.server.answer.repository;

import com.pre015.server.answer.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {}
