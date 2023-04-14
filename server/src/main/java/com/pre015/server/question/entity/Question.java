package com.pre_015.stackoverflow.question.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 100, nullable = false)
    private String title;
    @Column(length = 5000, nullable = false)
    private String content;
    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private QuestionStatus questionStatus = QuestionStatus.QUESTION_UNSOLVED;
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
    @Column(nullable = false)
    private LocalDateTime lastModifiedAt = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public void setMember(Member member) {
        this.member = member;
        if (!this.member.getQuestion().contains(this)) {
            this.member.getQuestion().add(this);
        }
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
