package com.pre015.server.member.entity;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.question.entity.Question;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "members")
@Builder


public class Member {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String displayName;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String about;

    @Column(nullable = true)
    private String img;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdDate;

    @Column(name = "last_modified_at")
    @LastModifiedDate
    private LocalDate last_modified_at;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus = MemberStatus.ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<Qustions> questions = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    public List<Question> getQuestion() {
        return questions;
    }

    public List<Answer> getAnswer() {
        return answers;
    }

    public List<Comment> getComment() {
        return comments;
    }
}







