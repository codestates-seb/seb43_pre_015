package com.pre015.server.question.repository;

import com.pre015.server.member.entity.Member;
import com.pre015.server.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByMember(Member member);
}
