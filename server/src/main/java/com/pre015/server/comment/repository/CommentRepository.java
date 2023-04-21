package com.pre015.server.comment.repository;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByMember(Member member);
    List<Comment> findByAnswer(Answer answer);
}
