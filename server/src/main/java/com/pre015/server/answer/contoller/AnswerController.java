package com.pre015.server.answer.contoller;

import com.pre015.server.answer.dto.AnswerDTO;
import com.pre015.server.answer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answers")
public class AnswerController {
    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping
    public ResponseEntity<AnswerDTO> createAnswer(@RequestBody AnswerDTO.POST answerPostDTO) {
        return new ResponseEntity<>(answerService.createAnswer(answerPostDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerDTO> updateAnswer(@PathVariable Long id, @RequestBody AnswerDTO answerDTO) {
        return new ResponseEntity<>(answerService.updateAnswer(id, answerDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<List<AnswerDTO>> findAllAnswers() {
        return new ResponseEntity<>(answerService.findAllAnswers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerDTO> findAnswerById(@PathVariable Long id) {
        return new ResponseEntity<>(answerService.findAnswer(id), HttpStatus.OK);
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<AnswerDTO>> findAnswersByMemberId(@PathVariable Long memberId) {
        return new ResponseEntity<>(answerService.findAnswersByMember(memberId), HttpStatus.OK);
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<List<AnswerDTO>> findAnswersByQuestionId(@PathVariable Long questionId) {
        return new ResponseEntity<>(answerService.findAnswersByQuestion(questionId), HttpStatus.OK);
    }

    @PutMapping("/accept/{questionId}/{answerId}")
    public ResponseEntity<Void> acceptAnswer(@PathVariable Long questionId, @PathVariable Long answerId) {
        answerService.acceptAnswer(questionId, answerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
