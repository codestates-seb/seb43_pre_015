package com.pre015.server.comment.mapper;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.comment.dto.CommentDto;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CommentMapper {

    public Comment postToComment(CommentDto.Post dto) {
        if(dto==null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setContent(dto.getContent());

        Answer answer = new Answer();
        answer.setAnswerId(dto.getAnswerId());
        comment.setAnswer(answer);

        Member member = new Member();
        member.setMemberId(dto.getMemberId());
        comment.setMember(member);

        return comment;
    }

    public Comment patchToComment(CommentDto.Patch dto) {
        if(dto == null) {
            return null;
        }

        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        comment.setCommentId(dto.getCommentId());

        Member member = new Member();
        member.setMemberId(dto.getMemberId());
        comment.setMember(member);

        Answer answer = new Answer();
        answer.setAnswerId(dto.getAnswerId());
        comment.setAnswer(answer);
        return comment;
    }

    public CommentDto.Response commentToResponse(Comment comment){
        if(comment == null) {
            return null;
        }

        return new CommentDto.Response(comment.getCommentId(),
                comment.getContent(),
                comment.getMember(),
                comment.getAnswer()
                );
    }

    public CommentDto.ResponseAll<Comment> commentsToResponseAll(List<Comment> comments,
                                                                 int page,
                                                                 int size,
                                                                 int totalElements,
                                                                 int totalPages) {
        for(Comment comment : comments) {
            if(comment.getContent().length() >= 50) {
                StringBuilder sb = new StringBuilder(comment.getContent().substring(0,47)).append("...");
                comment.setContent(sb.toString());
            }
        }

        return new CommentDto.ResponseAll(
                comments,page,size,totalElements,totalPages);
    }
}
