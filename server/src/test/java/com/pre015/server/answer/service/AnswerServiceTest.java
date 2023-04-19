package com.pre015.server.answer.service;

import com.pre015.server.answer.dto.AnswerDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.answer.mapper.AnswerMapper;
import com.pre015.server.answer.repository.AnswerRepository;
import com.pre015.server.member.entity.Member;
import com.pre015.server.member.service.MemberService;
import com.pre015.server.question.entity.Question;
import com.pre015.server.question.service.QuestionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AnswerServiceTest {

    @Mock
    private AnswerRepository answerRepository;

    @Mock
    private MemberService memberService;

    @Mock
    private QuestionService questionService;

    @Mock
    private AnswerMapper answerMapper;

    @InjectMocks
    private AnswerServiceImpl answerService;

    private Answer answer;
    private AnswerDTO answerDTO;
    private Member member;
    private Question question;

    public Question createQuestionWithId(long questionId, String title, String content) {
        Question question = new Question();
        try {
            Field questionIdField = Question.class.getDeclaredField("questionId");
            questionIdField.setAccessible(true);
            questionIdField.set(question, questionId);

            question.setTitle(title);
            question.setContent(content);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return question;
    }

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setMemberId(1L);

        question = createQuestionWithId(1L, "Test title", "Test content");
        question.setQuestionStatus(Question.QuestionStatus.QUESTION_UNSOLVED);

        answer = new Answer();
        answer.setAnswerId(1L);
        answer.setContent("Test answer");
        answer.setMember(member);
        answer.setQuestion(question);

        answerDTO = new AnswerDTO();
        answerDTO.setAnswerId(1L);
        answerDTO.setContent("Test answer");
        answerDTO.setMemberId(member.getMemberId());
        answerDTO.setQuestionId(question.getQuestionId());
    }

    @Test
    void createAnswer() {
        AnswerDTO.POST postDTO = new AnswerDTO.POST("Test answer", 1L, 1L);
        when(memberService.findVerifiedMember(postDTO.getMemberId())).thenReturn(member);
        when(questionService.findVerifiedQuestion(postDTO.getQuestionId())).thenReturn(question);
        when(answerMapper.PostDTOtoEntity(postDTO, member, question)).thenReturn(answer);
        when(answerRepository.save(answer)).thenReturn(answer);
        when(answerMapper.toDTO(answer)).thenReturn(answerDTO);

        AnswerDTO result = answerService.createAnswer(postDTO);

        verify(memberService, times(1)).findVerifiedMember(postDTO.getMemberId());
        verify(questionService, times(1)).findVerifiedQuestion(postDTO.getQuestionId());
        verify(answerMapper, times(1)).PostDTOtoEntity(postDTO, member, question);
        verify(answerRepository, times(1)).save(answer);
        verify(answerMapper, times(1)).toDTO(answer);

        assert(result.getContent().equals(answerDTO.getContent()));
    }

    @Test
    void updateAnswer() {
        when(answerRepository.findById(answer.getAnswerId())).thenReturn(Optional.of(answer));
        when(answerRepository.save(answer)).thenReturn(answer);
        when(answerMapper.toDTO(answer)).thenReturn(answerDTO);

        AnswerDTO result = answerService.updateAnswer(answer.getAnswerId(), answerDTO);

        verify(answerRepository, times(1)).findById(answer.getAnswerId());
        verify(answerRepository, times(1)).save(answer);
        verify(answerMapper, times(1)).toDTO(answer);
        assert(result.getContent().equals(answerDTO.getContent()));
    }

    @Test
    void deleteAnswer() {
        doNothing().when(answerRepository).deleteById(answer.getAnswerId());

        answerService.deleteAnswer(answer.getAnswerId());

        verify(answerRepository, times(1)).deleteById(answer.getAnswerId());
    }

    @Test
    void findAllAnswers() {
        List<Answer> answers = new ArrayList<>(Arrays.asList(answer));
        when(answerRepository.findAll()).thenReturn(answers);
        when(answerMapper.toDTOList(answers)).thenReturn(Arrays.asList(answerDTO));

        List<AnswerDTO> result = answerService.findAllAnswers();

        verify(answerRepository, times(1)).findAll();
        verify(answerMapper, times(1)).toDTOList(answers);

        assert(result.size() == 1);
        assert(result.get(0).getContent().equals(answerDTO.getContent()));
    }

    @Test
    void findAnswer() {
        when(answerRepository.findById(answer.getAnswerId())).thenReturn(Optional.of(answer));
        when(answerMapper.toDTO(answer)).thenReturn(answerDTO);

        AnswerDTO result = answerService.findAnswer(answer.getAnswerId());

        verify(answerRepository, times(1)).findById(answer.getAnswerId());
        verify(answerMapper, times(1)).toDTO(answer);

        assert(result.getContent().equals(answerDTO.getContent()));
    }

    @Test
    void findAnswersByMember() {
        List<Answer> answers = new ArrayList<>(Arrays.asList(answer));
        when(memberService.findVerifiedMember(member.getMemberId())).thenReturn(member);
        when(answerRepository.findByMember(member)).thenReturn(answers);
        when(answerMapper.toDTOList(answers)).thenReturn(Arrays.asList(answerDTO));

        List<AnswerDTO> result = answerService.findAnswersByMember(member.getMemberId());

        verify(memberService, times(1)).findVerifiedMember(member.getMemberId());
        verify(answerRepository, times(1)).findByMember(member);
        verify(answerMapper, times(1)).toDTOList(answers);

        assert(result.size() == 1);
        assert(result.get(0).getContent().equals(answerDTO.getContent()));
    }

    @Test
    void findAnswersByQuestion() {
        List<Answer> answers = new ArrayList<>(Arrays.asList(answer));
        when(questionService.findVerifiedQuestion(question.getQuestionId())).thenReturn(question);
        when(answerRepository.findByQuestion(question)).thenReturn(answers);
        when(answerMapper.toDTOList(answers)).thenReturn(Arrays.asList(answerDTO));

        List<AnswerDTO> result = answerService.findAnswersByQuestion(question.getQuestionId());

        verify(questionService, times(1)).findVerifiedQuestion(question.getQuestionId());
        verify(answerRepository, times(1)).findByQuestion(question);
        verify(answerMapper, times(1)).toDTOList(answers);

        assert(result.size() == 1);
        assert(result.get(0).getContent().equals(answerDTO.getContent()));
    }
}