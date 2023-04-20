package com.pre015.server.answer.contoller;

import com.pre015.server.answer.dto.AnswerPatchDTO;
import com.pre015.server.answer.dto.AnswerPostDTO;
import com.pre015.server.answer.dto.AnswerResponseDTO;
import com.pre015.server.answer.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<AnswerResponseDTO> createAnswer(@RequestBody AnswerPostDTO answerPostDTO) {
        return new ResponseEntity<>(answerService.createAnswer(answerPostDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AnswerResponseDTO> updateAnswer(@PathVariable Long id, @RequestBody AnswerPatchDTO answerPatchDTO) {
        return new ResponseEntity<>(answerService.updateAnswer(id, answerPatchDTO.getContent()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long id) {
        answerService.deleteAnswer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnswerResponseDTO> findAnswerById(@PathVariable Long id) {
        return new ResponseEntity<>(answerService.findAnswer(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<AnswerResponseDTO>> getAllAnswers(
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(answerService.findAllAnswers(pageable));
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<Page<AnswerResponseDTO>> getAnswersByMemberId(
            @PathVariable Long memberId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(answerService.findAnswersByMember(memberId, pageable));
    }

    @GetMapping("/question/{questionId}")
    public ResponseEntity<Page<AnswerResponseDTO>> getAnswersByQuestionId(
            @PathVariable Long questionId,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(answerService.findAnswersByQuestion(questionId, pageable));
    }

    @PutMapping("/accept/{questionId}/{answerId}")
    public ResponseEntity<Void> acceptAnswer(@PathVariable Long questionId, @PathVariable Long answerId) {
        answerService.acceptAnswer(questionId, answerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
