package com.pre015.server.question.controller;

import com.pre015.server.question.dto.QuestionDto;
import com.pre015.server.question.service.QuestionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
@Validated
public class QuestionController {
    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionDto.Response> postQuestion(@Valid @RequestBody QuestionDto.Post postDto){
        QuestionDto.Response response = questionService.createQuestion(postDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping("/{question_id}")
    public ResponseEntity<QuestionDto.Response> patchQuestion(@PathVariable("question_id") Long questionId,
                                                            @Valid @RequestBody QuestionDto.Patch patchDto){
        QuestionDto.Response response = questionService.updateQuestion(questionId, patchDto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{question_id}")
    public ResponseEntity<QuestionDto.DetailsResponse> getQuestion(@PathVariable("question_id") @Positive Long questionId){
        QuestionDto.DetailsResponse response = questionService.findQuestion(questionId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity getQuestions(@RequestParam("page") @Positive int page,
                                       @RequestParam("size") @Positive int size) {
        QuestionDto.MultiResponse response = questionService.findQuestions(page, size);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{question_id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable("question_id") @Positive Long questionId) {

        questionService.deleteQuestion(questionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
