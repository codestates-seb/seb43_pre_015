package com.pre015.server.answer.mapper;

import com.pre015.server.answer.dto.AnswerDTO;
import com.pre015.server.answer.entity.Answer;
import com.pre015.server.member.entity.Member;
import com.pre015.server.question.entity.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AnswerMapper {
    public Answer toEntity(AnswerDTO answerDTO, Member member, Question question) {
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());
        answer.setLikes(answerDTO.getLike());
        answer.setSelectionStatus(answerDTO.isSelectionStatus());
        answer.setMember(member);
        answer.setQuestion(question);
        return answer;
    }

    public Answer PostDTOtoEntity(AnswerDTO.POST answerDTO, Member member, Question question) {
        Answer answer = new Answer();
        answer.setContent(answerDTO.getContent());
        answer.setLikes(0);
        answer.setSelectionStatus(false);
        answer.setMember(member);
        answer.setQuestion(question);
        return answer;
    }

    public AnswerDTO toDTO(Answer answer) {
        return new AnswerDTO(answer);
    }

    public List<AnswerDTO> toDTOList(List<Answer> answers) {
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for (Answer answer : answers) {
            answerDTOList.add(toDTO(answer));
        }
        return answerDTOList;
    }
}
