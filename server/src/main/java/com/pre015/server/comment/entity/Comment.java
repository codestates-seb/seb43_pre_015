package com.pre015.server.comment.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Setter
    @Column(nullable = false, length = 1000)
    private String content;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime created_at;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modified_at;

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
