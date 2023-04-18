package com.pre015.server.comment.service;

import com.pre015.server.comment.entity.Comment;
import com.pre015.server.comment.repository.CommentRepository;
import com.pre015.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerService answerService;
    private final MemberService memberService;

    public Comment createComment(Comment comment){
        memberService.findVerifiedMember(comment.getMember().getMemberId());
        answerService.findAnswer(comment.getAnswer().getAnswerId());
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    // 예외처리 필요
    public Comment updateComment(Comment comment) {
        memberService.findVerifiedMember(comment.getMember().getMemberId());
        answerService.findAnswer(comment.getAnswer().getAnswerId());
        Optional<Comment> optionalComment = commentRepository.findById(comment.getCommentId());
        Comment findComment = optionalComment.orElseThrow(() -> null);

        Optional.ofNullable(comment.getContent()).ifPresent(content -> findComment.setContent(content));

        return findComment;
    }

    // 예외처리 필요
    @Transactional(readOnly = true)
    public Comment findComment(Long commentId)  {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() -> null);
        return comment;
    }

    // 예외처리 필요
    public void deleteComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() -> null);
        commentRepository.delete(comment);

    }
}
