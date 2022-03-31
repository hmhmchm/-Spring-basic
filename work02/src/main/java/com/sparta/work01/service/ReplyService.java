package com.sparta.work01.service;

import com.sparta.work01.dto.ReplyRequestDto;
import com.sparta.work01.model.Notice;
import com.sparta.work01.model.Reply;
import com.sparta.work01.model.User;
import com.sparta.work01.repository.NoticeRepository;
import com.sparta.work01.repository.ReplyRepository;
import com.sparta.work01.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final NoticeRepository noticeRepository;
    private final UserRepository userRepository;

    // 댓글 달기
    @Transactional
    public void saveReply(Notice notice, ReplyRequestDto replyRequestDto, User user) {

        Reply reply = Reply.builder().content(replyRequestDto.getContent()).build();
        Reply newReply = replyRepository.save(reply);
        notice.addReply(newReply);
        user.addReply(newReply);
    }
    // 댓글 수정하기
    @Transactional
    public void updateReply(Long replyId, String stringReply) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
        );
        reply.updateReply(stringReply);
    }
    // 댓글 삭제하기
    @Transactional
    public void deleteReply(Long noticeId, Long replyId, Long userId) {
        Reply deleteReply = replyRepository.findById(replyId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        Notice notice = noticeRepository.findById(noticeId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );

        notice.deleteReply(deleteReply);
        user.deleteReply(deleteReply);
        replyRepository.deleteById(deleteReply.getId());
    }


}
