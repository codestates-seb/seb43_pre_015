package com.pre015.server.answer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pre015.server.audit.BaseTimeEntity;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.member.entity.Member;
import com.pre015.server.question.entity.Question;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Table(indexes = {
        @Index(columnList = "content")
})
@Getter
@Entity
@NoArgsConstructor
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false, length = 5000)
    private String content;

    @Setter
    @Column(nullable = false)
    @ColumnDefault("0")
    private int like;

    @Setter
    @Column
    @ColumnDefault("False")
    private boolean selectionStatus;

    @Setter
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "member_id")
    private Member member;

    @Setter
    @ManyToOne(optional = false)
    @JsonBackReference
    @JoinColumn(name = "question_id")
    private Question question;

    @OneToMany(mappedBy = "answer")
    @JsonManagedReference
    private List<Comment> comments = new ArrayList<>();


    private Answer(Member member, Question question, String content) {
        this.member = member;
        this.question = question;
        this.content = content;
    }

    public static Answer of(Member member, Question question, String content) {
        return new Answer(member, question, content);
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
