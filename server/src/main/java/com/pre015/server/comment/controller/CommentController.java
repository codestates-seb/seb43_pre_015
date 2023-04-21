package com.pre015.server.comment.controller;

import com.pre015.server.comment.dto.CommentDto;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.comment.mapper.CommentMapper;
import com.pre015.server.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper mapper;

    /**
     * 새로운 comment 등록
     */
    @PostMapping("/new")
    public ResponseEntity postComment(@RequestBody @Valid CommentDto.Post postDto) {
        Comment comment = mapper.postToComment(postDto);
        commentService.createComment(comment);
        CommentDto.Response response = mapper.commentToResponse(comment);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }

    /**
     * 기존의 comment 수정
     */

    @PatchMapping("/{comment_id}")
    public ResponseEntity updateComment(
            @PathVariable("comment_id") @Positive Long commentId,
            @Valid @RequestBody CommentDto.Patch patchDto) {
        patchDto.setCommentId(commentId);
        Comment comment = mapper.patchToComment(patchDto);
        CommentDto.Response response = mapper.commentToResponse(comment);
        return new ResponseEntity(response,HttpStatus.OK);
    }

    /**
     *  댓글 하나만 조회
     */
    @GetMapping("/{comment_id}")
    public ResponseEntity getComment(@PathVariable("comment_id") Long commentId) {
        Comment comment = commentService.findComment(commentId);
        CommentDto.Response response = mapper.commentToResponse(comment);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    /**
     *  멤버 Id로 작성한 댓글 전부 조회
     */
    @GetMapping("/member/{member_id}")
    public ResponseEntity getCommentsByMember(
            @PathVariable("member_id") Long memberId,
            @Positive @RequestParam int page,
            @Positive @RequestParam int size) {
        Page<Comment> commentsByMember = commentService.findCommentsByMember(memberId, page - 1, size);
        List<Comment> comments = commentsByMember.getContent();

        CommentDto.ResponseAll<List<CommentDto.Response>> listResponseAll = mapper.commentsToResponseAll(comments, page, size, (int) commentsByMember.getTotalElements(), commentsByMember.getTotalPages());
        return new ResponseEntity(listResponseAll,HttpStatus.OK);
    }

    /**
     * 답변id로 댓글을 모두 검색
     * @param answerId 답변 id
     * @param page 페이지
     * @param size 한 페이지에 몇개의 오브젝트가 들어가는 지
     * @return
     */
    @GetMapping("/answer/{answer_id}")
    public ResponseEntity getCommentsByAnswer(
            @PathVariable("answer_id") Long answerId,
            @Positive @RequestParam int page,
            @Positive @RequestParam int size) {
        Page<Comment> commentsByAnswer = commentService.findCommentsByAnswer(answerId, page - 1, size);
        List<Comment> comments = commentsByAnswer.getContent();

        CommentDto.ResponseAll<List<CommentDto.Response>> listResponseAll = mapper.commentsToResponseAll(comments, page, size, (int) commentsByAnswer.getTotalElements(), commentsByAnswer.getTotalPages());
        return new ResponseEntity(listResponseAll,HttpStatus.OK);
    }



    @DeleteMapping("/{comment_id}")
    public ResponseEntity deleteComment(@PathVariable("comment_id") Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}

