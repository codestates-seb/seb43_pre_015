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
        return new ResponseEntity<Comment>(comment, HttpStatus.CREATED);
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
        return new ResponseEntity(comment,HttpStatus.OK);
    }

    /**
     *
     */

    @GetMapping("/{comment_id}")
    public ResponseEntity getComment(@PathVariable("comment_id") Long commentId) {
        Comment comment = commentService.findComment(commentId);
        CommentDto.Response response = mapper.commentToResponse(comment);
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity getComments(
            @Positive @RequestParam int page,
            @Positive @RequestParam int size) {
        Page<Comment> pageComments = commentService.findComments(page - 1, size);
        List<Comment> comments = pageComments.getContent();

        mapper.commentsToResponseAll(comments, page, size, (int) pageComments.getTotalElements(), pageComments.getTotalPages());
        return null;
    }

    @DeleteMapping("/{comment_id}")
    public void deleteComment(@PathVariable("comment_id") Long commentId) {
        Comment comment = commentService.findComment(commentId);
        commentService.deleteComment(comment.getCommentId());
    }

}

