package com.pre015.server.answer.entity;

import com.pre015.server.audit.BaseTimeEntity;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.member.entity.Member;
import com.pre015.server.question.entity.Question;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content")
})
@Entity
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @ManyToOne(optional = false)
    @JoinColumn(name = "member_id")
    private Member member;
    @Setter @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;
    @Setter @Column(nullable = false, length = 5000) private String content;

    @Setter
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AnswerStatus answerStatus;

    @OneToMany(mappedBy = "answer")
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComment() {
        return comments;
    }

    protected Answer() {
    }

    private Answer(Member member, Question question, String content, AnswerStatus answerStatus) {
        this.member = member;
        this.question = question;
        this.content = content;
        this.answerStatus = answerStatus;
    }

    public static Answer of(Member member, Question question, String content, AnswerStatus answerStatus) {
        return new Answer(member, question, content, answerStatus);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;
        Answer answer = (Answer) o;
        return id != null && id.equals(answer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
