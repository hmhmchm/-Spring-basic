package com.sparta.work01.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter // get 함수를 일괄적으로 만들어줍니다.
@Builder
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자를 만들어줍니다.
@Entity // DB 테이블 역할을 합니다.
public class User {

    // ID가 자동으로 생성 및 증가합니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    // nullable: null 허용 여부
// unique: 중복 허용 여부 (false 일때 중복 허용)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Notice> notices = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Reply> replyList = new HashSet<>();

    public User(String username, String password, String email, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = null;
    }

    public User(String username, String password, String email, UserRoleEnum role, Long kakaoId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.kakaoId = kakaoId;
    }

    // notice 추가 삭제
    public void addNotice(Notice notice) {
        this.notices.add(notice);
        notice.setUser(this);
    }

    public void deleteNotice(Notice notice) {
        this.notices.remove(notice);
        notice.setUser(null);
    }
    // reply 추가 삭제
    public void addReply(Reply reply) {
        this.replyList.add(reply);
        reply.setUser(this);
    }

    public void deleteReply(Reply reply) {
        this.replyList.remove(reply);
        reply.setUser(null);
    }
}