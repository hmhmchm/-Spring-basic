package com.sparta.work01.controller;

import com.sparta.work01.domain.Notice;
import com.sparta.work01.domain.NoticeRepository;
import com.sparta.work01.domain.NoticeRequestDto;
import com.sparta.work01.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoticeController {
    private final NoticeRepository noticeRepository;
    private final NoticeService noticeService;

    // 글 작성하기
    @PostMapping("/api/notices")
    public Notice createNotice(@RequestBody NoticeRequestDto requestDto){
        Notice notice = new Notice(requestDto);
        return noticeRepository.save(notice);
    }
    // 글 목록보기
    @GetMapping("/api/notices")
    public List<Notice> getNotice(){
        return noticeRepository.findAllByOrderByModifiedAtDesc();
    }
    // 상세내용 보기
    @GetMapping("/api/notices/{id}")
    public Notice getDetailNotice(@PathVariable Long id){
        return noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
    }

}
