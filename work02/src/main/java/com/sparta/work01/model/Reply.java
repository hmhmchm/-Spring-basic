package com.sparta.work01.model;

import com.sparta.work01.dto.ReplyRequestDto;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor // 기본 생성자
@Getter
@Setter
@Entity // 테이블과 연계됨을 스프링에게 알려줍니다.
public class Reply extends Timestamped{

    @GeneratedValue
    @Id
    private Long id; // 댓글 번호

    @Column(nullable = false, length = 500)
    private String content; // 댓글 내용

    @ManyToOne // 1 : N
    private Notice notice; // 게시글 번호

    @ManyToOne // 1 : N
    private User user; // 로그인한 사용자

    public void updateReply(String reply) {
        this.content = reply;
    }

    public Reply(ReplyRequestDto requestDto){
        this.content = requestDto.getContent();
    }
//    public void save(Notice notice, User user){
//        this.notice = notice;
//        this.user = user;
//    }
}
