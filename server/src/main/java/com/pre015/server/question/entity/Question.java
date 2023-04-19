package com.pre015.server.question.entity;

import com.pre015.server.audit.BaseTimeEntity;
import com.pre015.server.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 5000, nullable = false)
    private String content;
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_UNSOLVED;
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if (!this.member.getQuestions().contains(this)) {
            this.member.getQuestions().add(this);
        }
    }
    public void setTitle(String setTitle){
        if(title.length() <= 100) {
            this.title = setTitle;
        } else{
            throw new IllegalArgumentException("title length must be less than 100");
        }
    }
    public void setContent(String setContent){
        if(content.length() <= 5000) {
            this.content = setContent;
        } else{
            throw new IllegalArgumentException("content length must be less than 5,000");
        }
    }
    public void setQuestionStatus(Question.QuestionStatus questionStatus){
        this.questionStatus = questionStatus;
    }

    public enum QuestionStatus {
        QUESTION_UNSOLVED("채택된 답변 없음"),
        QUESTION_SOLVED("답변완료");

        @Getter
        private String status;

        QuestionStatus(String status) {
            this.status = status;
        }
    }
}
