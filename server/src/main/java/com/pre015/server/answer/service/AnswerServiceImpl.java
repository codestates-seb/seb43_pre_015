package com.pre015.server.answer.service;

import com.pre015.server.answer.dto.AnswerPostDTO;
import com.pre015.server.answer.dto.AnswerResponseDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.answer.mapper.AnswerMapper;
import com.pre015.server.answer.repository.AnswerRepository;
import com.pre015.server.member.entity.Member;
import com.pre015.server.member.service.MemberService;
import com.pre015.server.question.entity.Question;
import com.pre015.server.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    private final AnswerRepository answerRepository;
    private final MemberService memberService;
    private final QuestionService questionService;
    private final AnswerMapper answerMapper;

    @Autowired
    public AnswerServiceImpl(AnswerRepository answerRepository, MemberService memberService,
                             QuestionService questionService, AnswerMapper answerMapper) {
        this.answerRepository = answerRepository;
        this.memberService = memberService;
        this.questionService = questionService;
        this.answerMapper = answerMapper;
    }

    @Override
    public AnswerResponseDTO createAnswer(AnswerPostDTO answerDTO) {
        Member member = memberService.findVerifiedMember(answerDTO.getMemberId());
        Question question = questionService.findVerifiedQuestion(answerDTO.getQuestionId());
        Answer answer = answerMapper.postDTOtoEntity(answerDTO, member, question);
        return answerMapper.entityToAnswerResponseDto(answerRepository.save(answer), member, question);
    }

    @Override
    public AnswerResponseDTO updateAnswer(Long answerId, String content) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        answer.setContent(content);
        return answerMapper.toResponseDTO(answerRepository.save(answer));
    }

    @Override
    public void deleteAnswer(Long answerId) {
        answerRepository.deleteById(answerId);
    }

    @Override
    public AnswerResponseDTO findAnswer(Long answerId) {
        Answer answer = answerRepository.findById(answerId)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        return answerMapper.toResponseDTO(answer);
    }

    @Override
    public Page<AnswerResponseDTO> findAllAnswers(Pageable pageable) {
        return answerRepository.findAll(pageable).map(answerMapper::toResponseDTO);
    }

    @Override
    public Page<AnswerResponseDTO> findAnswersByMember(Long memberId, Pageable pageable) {
        Member member = memberService.findVerifiedMember(memberId);
        return answerRepository.findByMember(member, pageable).map(answerMapper::toResponseDTO);
    }

    @Override
    public Page<AnswerResponseDTO> findAnswersByQuestion(Long questionId, Pageable pageable) {
        Question question = questionService.findVerifiedQuestion(questionId);
        return answerRepository.findByQuestion(question, pageable).map(answerMapper::toResponseDTO);
    }

    @Transactional
    @Override
    public void acceptAnswer(Long questionId, Long answerId) {
        Question question = questionService.findVerifiedQuestion(questionId);
        AnswerResponseDTO answerDTO = findAnswer(answerId);
        Member memberForMember = memberService.findVerifiedMember(answerDTO.getMemberId());
        Question questionForAnswer = questionService.findVerifiedQuestion(answerDTO.getQuestionId());
        Answer answer = answerMapper.responseDtoToEntity(answerDTO, memberForMember, questionForAnswer);

        if (question.getQuestionStatus() == Question.QuestionStatus.QUESTION_SOLVED) {
            throw new RuntimeException("The question already has an accepted answer");
        }

        answer.setSelectionStatus(true);
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_SOLVED);

        answerRepository.save(answer);
        questionService.updateQuestion(question);
    }
}
