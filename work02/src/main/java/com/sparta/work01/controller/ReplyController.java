package com.sparta.work01.controller;

import com.sparta.work01.dto.NoticeRequestDto;
import com.sparta.work01.dto.ReplyRequestDto;
import com.sparta.work01.model.Notice;
import com.sparta.work01.model.Reply;
import com.sparta.work01.model.User;
import com.sparta.work01.repository.NoticeRepository;
import com.sparta.work01.repository.ReplyRepository;
import com.sparta.work01.security.UserDetailsImpl;
import com.sparta.work01.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;
    private final NoticeRepository noticeRepository;
    private final ReplyRepository replyRepository;

    // 조회하기
    @GetMapping("/api/replys/{noticeId}")
    public List<Reply> getReplysByRecipeNo(@PathVariable Long noticeId) {

        return replyRepository.findByNoticeId(noticeId);
    }
    // 댓글 남기기

//    @PostMapping("/api/replys/{noticeId}")
//    public Reply createReply(@RequestBody ReplyRequestDto requestDto,@PathVariable Long noticeId,@AuthenticationPrincipal UserDetailsImpl userDetails){
//        Reply reply = new Reply(requestDto);
//        return replyRepository.save(reply);
//    }

//    @GetMapping("/api/notices")
//    public List<Notice> getNotice(){
//        return noticeRepository.findAllByOrderByModifiedAtDesc();
//    }

    @PostMapping("/api/replys/{noticeId}")
    public ResponseEntity saveReply(@PathVariable Long noticeId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ReplyRequestDto replyRequestDto) {
        Notice notice = noticeRepository.findById(noticeId).orElse(null);
        if(notice == null) {
            return ResponseEntity.badRequest().build();
        }
        replyService.saveReply(notice, replyRequestDto, userDetails.getUser());
        System.out.println("게시물 번호 : " +noticeId);
        System.out.println(notice.toString());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/notices/replys/{noticeId}/{replyId}")
    public void updateReply(@PathVariable Long noticeId, @PathVariable Long replyId,
                              @RequestBody String reply) throws Exception {
        replyService.updateReply(replyId, reply);
    }

    @DeleteMapping("/notices/replys/{noticeId}/{replyId}")
    public void deleteReply(@PathVariable Long noticeId, @PathVariable Long replyId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        replyService.deleteReply(noticeId, replyId, userDetails.getUser().getId());
    }

};
