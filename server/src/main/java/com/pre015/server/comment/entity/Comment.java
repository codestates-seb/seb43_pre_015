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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Setter
    @Column(nullable = false, length = 1000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "answer_id")
    private Answer answer;

    public Comment(String content) {
        this.content = content;
    }

    public void setMember(Member member) {
        this.member = member;
        if(!this.member.getComment().contains(this)) {
            this.member.getComment().add(this);
        }
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
        if (!this.answer.getComment().contains(this)) {
            this.answer.getComment().add(this);
        }
    }
}
