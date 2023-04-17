package com.pre015.server.comment.controller;

import com.pre015.server.comment.dto.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/comment")
public class CommentController {


//    @PostMapping("/new")
//    public ResponseEntity postComment(@RequestBody @Valid CommentDto.Post postDto) {
//
//    }
}
