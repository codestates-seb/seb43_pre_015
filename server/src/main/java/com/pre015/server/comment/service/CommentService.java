package com.pre015.server.comment.service;

import com.pre015.server.answer.service.AnswerService;
import com.pre015.server.auth.interceptor.JwtParseInterceptor;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.comment.repository.CommentRepository;

import com.pre015.server.exception.BusinessLogicException;
import com.pre015.server.exception.ExceptionCode;
import com.pre015.server.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final AnswerService answerService;
    private final MemberService memberService;

    public Comment createComment(Comment comment){
        // JwtParseInterceptor에서 extract한 memberId를 얻는다.
        long authenticatedMemberId = JwtParseInterceptor.getAuthenticatedMemberId();
        if (comment.getMember().getMemberId() != authenticatedMemberId) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_ID_DIFFERENT);
        }
        memberService.findVerifiedMember(comment.getMember().getMemberId());
        answerService.findAnswer(comment.getAnswer().getAnswerId());
        Comment savedComment = commentRepository.save(comment);
        return savedComment;
    }

    public Comment updateComment(Comment comment) {
        memberService.findVerifiedMember(comment.getMember().getMemberId());
        answerService.findAnswer(comment.getAnswer().getAnswerId());
        Optional<Comment> optionalComment = commentRepository.findById(comment.getCommentId());
        Comment findComment = optionalComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));

        Optional.ofNullable(comment.getContent()).ifPresent(content -> findComment.setContent(content));

        return findComment;
    }

    @Transactional(readOnly = true)
    public Comment findComment(Long commentId)  {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() -> new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        return comment;
    }


         public void deleteComment(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        Comment comment = optionalComment.orElseThrow(() ->  new BusinessLogicException(ExceptionCode.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }


    public Page<Comment> findCommentsByMember(Long memberId, int page, int size) {
        List<Comment> byMember = commentRepository.findByMember(memberService.findVerifiedMember(memberId));
        Page<Comment> commentsByMember = convertListToPage(byMember, page, size);
        return commentsByMember;
    }

    public Page<Comment> findCommentsByAnswer(Long answerId, int page, int size) {
        List<Comment> byAnswer = commentRepository.findByMember(memberService.findVerifiedMember(answerId));
        Page<Comment> commentsByAnswer = convertListToPage(byAnswer, page, size);
        return commentsByAnswer;
    }




    public <T> Page<T> convertListToPage(List<T> list, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);

        int total = list.size();

        int start = (int) pageable.getOffset();
        int end = Math.min(start+ pageable.getPageSize(), total);
        List<T> subList = list.subList(start,end);

        return new PageImpl<>(subList,pageable,total);
    }
}

