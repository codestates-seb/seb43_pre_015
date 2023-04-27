package com.pre015.server.answer.mapper;

import com.pre015.server.answer.dto.AnswerPostDTO;
import com.pre015.server.answer.dto.AnswerResponseDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.member.entity.Member;
import com.pre015.server.question.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerMapper {
    public Answer responseDtoToEntity(AnswerResponseDTO answerDTO, Member member, Question question) {
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());
        answer.setLikes(answerDTO.getLikes());
        answer.setSelectionStatus(answerDTO.isSelectionStatus());
        answer.setMember(member);
        answer.setQuestion(question);
        return answer;
    }

    public Answer postDTOtoEntity(AnswerPostDTO answerDTO, Member member, Question question) {
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());
        answer.setLikes(0);
        answer.setSelectionStatus(false);
        answer.setMember(member);
        answer.setQuestion(question);
        return answer;
    }

    public AnswerResponseDTO entityToAnswerResponseDto(Answer answer, Member member, Question question) {
        AnswerResponseDTO answerDTO = new AnswerResponseDTO();
        answerDTO.setAnswerId(answerDTO.getAnswerId());
        answerDTO.setContent(answer.getContent());
        answerDTO.setLikes(0);
        answerDTO.setSelectionStatus(false);
        answerDTO.setCreatedTime(answer.getCreatedTime());
        answerDTO.setModifiedTime(answer.getModifiedTime());
        answerDTO.setMemberId(member.getMemberId());
        answerDTO.setMemberDisplayName(member.getDisplayName());
        answerDTO.setQuestionId(question.getQuestionId());
        return answerDTO;
    }

    public AnswerResponseDTO toResponseDTO(Answer answer) {
        return new AnswerResponseDTO(answer);
    }

    public List<AnswerResponseDTO> toResponseDTOList(List<Answer> answers) {
        List<AnswerResponseDTO> answerDTOList = new ArrayList<>();
        for (Answer answer : answers) {
            answerDTOList.add(toResponseDTO(answer));
        }
        return answerDTOList;
    }
}
