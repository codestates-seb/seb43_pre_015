package com.pre015.server.member.entity;

import com.pre015.server.answer.entity.Answer;
import com.pre015.server.audit.BaseTimeEntity;
import com.pre015.server.comment.entity.Comment;
import com.pre015.server.question.entity.Question;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "members")
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false, unique = true)
    private String displayName;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus memberStatus = MemberStatus.ACTIVE;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "member")
    private List<Question> questions = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "member")
    private List<Answer> answers = new ArrayList<>();

    @ElementCollection(fetch = FetchType.LAZY)
    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

}







