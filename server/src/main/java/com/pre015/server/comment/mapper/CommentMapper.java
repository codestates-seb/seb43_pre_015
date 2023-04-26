package com.pre015.server.comment.mapper;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.comment.dto.CommentDto;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.member.entity.Member;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
                comment.getMember().getMemberId(),
                comment.getAnswer().getAnswerId(),
                comment.getCreatedTime(),
                comment.getModifiedTime()
                );
    }

    public CommentDto.ResponseAll<List<CommentDto.Response>> commentsToResponseAll(List<Comment> comments,
                                                                 int page,
                                                                 int size,
                                                                 int totalElements,
                                                                 int totalPages) {
        List<CommentDto.Response> newComments = new ArrayList<>();
        for(Comment comment : comments) {
            if(comment.getContent().length() >= 50) {
                StringBuilder sb = new StringBuilder(comment.getContent().substring(0,47)).append("...");
                comment.setContent(sb.toString());
            }
            newComments.add(commentToResponse(comment));
        }

        return new CommentDto.ResponseAll<>(
                newComments,page,size,totalElements,totalPages);
    }
}
