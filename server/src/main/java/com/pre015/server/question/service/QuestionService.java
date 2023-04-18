package com.pre015.server.question.service;

import com.pre015.server.member.entity.Member;
import com.pre015.server.member.service.MemberService;
import com.pre015.server.question.dto.QuestionDto;
import com.pre015.server.question.entity.Question;
import com.pre015.server.question.mapper.QuestionMapper;
import com.pre015.server.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final QuestionMapper mapper;
    private final MemberService memberService;

    public QuestionDto.Response createQuestion(QuestionDto.Post postDto){
        Question question = mapper.questionPostDtoToQuestion(postDto);
        Member member = memberService.findVerifiedMember(question.getMember().getMemberId());
        question.setMember(member);

        Question savedQuestion = this.questionRepository.save(question);
        return mapper.questionToQuestionResponseDto(savedQuestion);
    }

    public QuestionDto.Response updateQuestion(long questionId, QuestionDto.Patch patchDto){
        patchDto.setQuestionId(questionId);
        Question question = mapper.questionPatchDtoToQuestion(patchDto);

        Question findQuestion = findVerifiedQuestion(question.getQuestionId());
        Optional.ofNullable(question.getTitle())
                .ifPresent(findQuestion::setTitle);
        Optional.ofNullable(question.getContent())
                .ifPresent(findQuestion::setContent);

        Question updatedQuestion = questionRepository.save(findQuestion);
        return mapper.questionToQuestionResponseDto(updatedQuestion);
    }

    public QuestionDto.DetailsResponse findQuestion(long questionId){
        Question foundQuestion = findVerifiedQuestion(questionId);
        return mapper.questionToQuestionDetailsResponseDto(foundQuestion);
    }

    public QuestionDto.MultiResponse findQuestions(int page, int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by("questionId").descending());
        Page<Question> questionPage = questionRepository.findAll(pageRequest);
        List<QuestionDto.Response> responseDtos = mapper.questionsToQuestionResponseDtos(questionPage.getContent());
        QuestionDto.PageInfo pageInfo = new QuestionDto.PageInfo(questionPage.getNumber() + 1, questionPage.getSize(), questionPage.getTotalElements(), questionPage.getTotalPages());

        return new QuestionDto.MultiResponse<>(responseDtos, pageInfo);
    }

    public void deleteQuestion(long questionId) {
        Question question = findVerifiedQuestion(questionId);
        questionRepository.delete(question);
    }

    public Question findVerifiedQuestion(long questionId) {
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        return optionalQuestion.orElseThrow(() -> new RuntimeException("question not found"));
    }
}
