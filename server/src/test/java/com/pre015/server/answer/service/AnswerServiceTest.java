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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

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

    private Pageable pageable = PageRequest.of(0, 10);
    private Answer answer;
    private AnswerResponseDTO answerResponseDTO;
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

        answerResponseDTO = new AnswerResponseDTO();
        answerResponseDTO.setAnswerId(1L);
        answerResponseDTO.setContent("Test answer");
        answerResponseDTO.setMemberId(member.getMemberId());
        answerResponseDTO.setMemberDisplayName(member.getDisplayName());
        answerResponseDTO.setQuestionId(question.getQuestionId());
        answerResponseDTO.setCreatedTime(LocalDateTime.now());
        answerResponseDTO.setModifiedTime(LocalDateTime.now());
    }

    @Test
    void createAnswer() {
        AnswerPostDTO postDTO = new AnswerPostDTO("Test answer", 1L, 1L);
        when(memberService.findVerifiedMember(postDTO.getMemberId())).thenReturn(member);
        when(questionService.findVerifiedQuestion(postDTO.getQuestionId())).thenReturn(question);
        when(answerMapper.postDTOtoEntity(postDTO, member, question)).thenReturn(answer);
        when(answerRepository.save(answer)).thenReturn(answer);
        when(answerMapper.entityToAnswerResponseDto(answer, member, question)).thenReturn(answerResponseDTO);

        AnswerResponseDTO result = answerService.createAnswer(postDTO);

        verify(memberService, times(1)).findVerifiedMember(postDTO.getMemberId());
        verify(questionService, times(1)).findVerifiedQuestion(postDTO.getQuestionId());
        verify(answerMapper, times(1)).postDTOtoEntity(postDTO, member, question);
        verify(answerRepository, times(1)).save(answer);
        verify(answerMapper, times(1)).entityToAnswerResponseDto(answer, member, question);

        assert (result.getContent().equals(answerResponseDTO.getContent()));
    }

    @Test
    void updateAnswer() {
        when(answerRepository.findById(answer.getAnswerId())).thenReturn(Optional.of(answer));
        when(answerRepository.save(answer)).thenReturn(answer);
        when(answerMapper.toResponseDTO(answer)).thenReturn(answerResponseDTO);

        AnswerResponseDTO result = answerService.updateAnswer(answer.getAnswerId(), answerResponseDTO.getContent());

        verify(answerRepository, times(1)).findById(answer.getAnswerId());
        verify(answerRepository, times(1)).save(answer);
        verify(answerMapper, times(1)).toResponseDTO(answer);
        assert (result.getContent().equals(answerResponseDTO.getContent()));
    }

    @Test
    void deleteAnswer() {
        doNothing().when(answerRepository).deleteById(answer.getAnswerId());

        answerService.deleteAnswer(answer.getAnswerId());

        verify(answerRepository, times(1)).deleteById(answer.getAnswerId());
    }

    @Test
    void findAnswer() {
        when(answerRepository.findById(answer.getAnswerId())).thenReturn(Optional.of(answer));
        when(answerMapper.toResponseDTO(answer)).thenReturn(answerResponseDTO);

        AnswerResponseDTO result = answerService.findAnswer(answer.getAnswerId());

        verify(answerRepository, times(1)).findById(answer.getAnswerId());
        verify(answerMapper, times(1)).toResponseDTO(answer);

        assert (result.getContent().equals(answerResponseDTO.getContent()));
    }

    @Test
    void findAllAnswers() {
        int page = 0, size = 10;
        List<Answer> answers = new ArrayList<>(Arrays.asList(answer));
        Page<Answer> answerPage = new PageImpl<>(answers, pageable, answers.size());
        when(answerRepository.findAll(pageable)).thenReturn(answerPage);
        when(answerMapper.toResponseDTO(answers.get(0))).thenReturn(answerResponseDTO);

        Page<AnswerResponseDTO> result = answerService.findAllAnswers(page, size);

        verify(answerRepository, times(1)).findAll(pageable);
        verify(answerMapper, times(1)).toResponseDTO(answers.get(0));

        assert (result.getContent().size() == 1);
        assert (result.getContent().get(0).getContent().equals(answerResponseDTO.getContent()));
    }

    @Test
    void findAnswersByMember() {
        int page = 0, size = 10;
        List<Answer> answers = new ArrayList<>(Arrays.asList(answer));
        Page<Answer> answerPage = new PageImpl<>(answers, pageable, answers.size());
        when(memberService.findVerifiedMember(member.getMemberId())).thenReturn(member);
        when(answerRepository.findByMember(member, pageable)).thenReturn(answerPage);
        when(answerMapper.toResponseDTO(answers.get(0))).thenReturn(answerResponseDTO);

        Page<AnswerResponseDTO> result = answerService.findAnswersByMember(member.getMemberId(), page, size);

        verify(memberService, times(1)).findVerifiedMember(member.getMemberId());
        verify(answerRepository, times(1)).findByMember(member, pageable);
        verify(answerMapper, times(1)).toResponseDTO(answers.get(0));

        assert (result.getContent().size() == 1);
        assert (result.getContent().get(0).getContent().equals(answerResponseDTO.getContent()));
    }

    @Test
    void findAnswersByQuestion() {
        int page = 0, size = 10;
        List<Answer> answers = new ArrayList<>(Arrays.asList(answer));
        Page<Answer> answerPage = new PageImpl<>(answers, pageable, answers.size());
        when(questionService.findVerifiedQuestion(question.getQuestionId())).thenReturn(question);
        when(answerRepository.findByQuestion(question, pageable)).thenReturn(answerPage);
        when(answerMapper.toResponseDTO(answers.get(0))).thenReturn(answerResponseDTO);

        Page<AnswerResponseDTO> result = answerService.findAnswersByQuestion(question.getQuestionId(), page, size);

        verify(questionService, times(1)).findVerifiedQuestion(question.getQuestionId());
        verify(answerRepository, times(1)).findByQuestion(question, pageable);
        verify(answerMapper, times(1)).toResponseDTO(answers.get(0));

        assert (result.getContent().size() == 1);
        assert (result.getContent().get(0).getContent().equals(answerResponseDTO.getContent()));
    }
}