package com.sparta.work01.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sparta.work01.dto.NoticeRequestDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @ManyToOne // 1 : N
    private User user; // 로그인한 사용자

    @OrderBy("id desc") // 댓글 최신순으로 나열
    @Builder.Default
    @JsonIgnore // notice를 조회하면 댓글을 조회하고 댓글 조회하면 notice 조회하고 무한 반복이다, 그걸 방지
    @OneToMany(mappedBy = "notice", fetch = FetchType.EAGER)
    private Set<Reply> replyList = new HashSet<>();

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

    // 댓글 추가 삭제
    public void addReply(Reply reply) {
        this.replyList.add(reply);
        reply.setNotice(this);
    }

    public void deleteReply(Reply deleteReply) {
        this.replyList.remove(deleteReply);
        deleteReply.setNotice(null);
    }
}
