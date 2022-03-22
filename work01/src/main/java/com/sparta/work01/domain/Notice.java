package com.sparta.work01.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor // 기본 생성자
@Getter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Notice extends Timestamped{
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id; // 글 번호

    @Column(nullable = false)
    private String username; // 작성자

    @Column(nullable = false)
    private String title; // 글 제목

    @Column(nullable = false)
    private String contents; // 글 내용

    public Notice(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    // 게시판 dto
    public Notice(NoticeRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
    // 게시판 update 는 나중에하자 요구사항에 수정이 없다.
    public void update(NoticeRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
