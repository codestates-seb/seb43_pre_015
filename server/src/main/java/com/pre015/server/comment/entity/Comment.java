package com.pre015.server.comment.entity;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.audit.BaseTimeEntity;
import com.pre015.server.member.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Comment extends BaseTimeEntity {

    @Id
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Setter
    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public Comment(String content,Member member, Answer answer) {
        this.content = content;
        this.member = member;
        this.answer = answer;
    }

    public void setMember(Member member) {
        this.member = member;
        if(!this.member.getComments().contains(this)) {
            this.member.getComments().add(this);
        }
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        if (!this.answer.getComments().contains(this)) {
            this.answer.getComments().add(this);
        }
    }
}