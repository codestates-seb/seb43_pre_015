package com.pre015.server.answer.service;

import com.pre015.server.answer.dto.AnswerDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.answer.mapper.AnswerMapper;
import com.pre015.server.answer.repository.AnswerRepository;
import com.pre015.server.member.entity.Member;
import com.pre015.server.member.service.MemberService;
import com.pre015.server.question.entity.Question;
import com.pre015.server.question.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public AnswerDTO createAnswer(AnswerDTO answerDTO) {
        Member member = memberService.findMemberById(answerDTO.getMemberId());
        Question question = questionService.findQuestionById(answerDTO.getQuestionId());
        Answer answer = answerMapper.toEntity(answerDTO, member, question);
        return answerMapper.toDTO(answerRepository.save(answer));
    }

    @Override
    public AnswerDTO updateAnswer(Long id, AnswerDTO answerDTO) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        answer.setContent(answerDTO.getContent());
        return answerMapper.toDTO(answerRepository.save(answer));
    }

    @Override
    public void deleteAnswer(Long id) {
        answerRepository.deleteById(id);
    }

    @Override
    public List<AnswerDTO> findAllAnswers() {
        return answerMapper.toDTOList(answerRepository.findAll());
    }

    @Override
    public AnswerDTO findAnswerById(Long id) {
        Answer answer = answerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Answer not found"));
        return answerMapper.toDTO(answer);
    }

    @Override
    public List<AnswerDTO> findAnswersByMemberId(Long memberId) {
        return answerMapper.toDTOList(answerRepository.findByMemberId(memberId));
    }

    @Override
    public List<AnswerDTO> findAnswersByQuestionId(Long questionId) {
        return answerMapper.toDTOList(answerRepository.findByQuestionId(questionId));
    }

    @Transactional
    @Override
    public void acceptAnswer(Long questionId, Long answerId) {
        Question question = questionService.findQuestionById(questionId);
        AnswerDTO answerDTO = findAnswerById(answerId);
        Member memberForMember = memberService.findMemberById(answerDTO.getMemberId());
        Question questionForAnswer = questionService.findQuestionById(answerDTO.getQuestionId());
        Answer answer = answerMapper.toEntity(answerDTO, memberForMember, questionForAnswer);

        if (question.getQuestionStatus() == Question.QuestionStatus.QUESTION_SOLVED) {
            throw new RuntimeException("The question already has an accepted answer");
        }

        answer.setSelectionStatus(true);
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_SOLVED);

        answerRepository.save(answer);
        questionService.updateQuestion(question);
    }
}
