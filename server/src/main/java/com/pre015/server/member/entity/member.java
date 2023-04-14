package com.pre015.server.member.entity;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "member")


public class member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String display_name;

    @Column(nullable = false, updatable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "created_at")
    @CreatedDate
    private LocalDate createdDate;

    @Column(name = "last_modified_at")
    @LastModifiedDate
    private LocalDate last_modified_at;

    //@Enumerated(value = EnumType.STRING)
    //Column(length = 20, nullable = false)
    // private MemberStatus memberStatus = = MemberStatus.USER_ACTIVE;



    //@Column(length = 20, nullable = false)
    //private MemberStatus memberStatus = MemberStatus.MEMBER_ACTIVE;



    }


