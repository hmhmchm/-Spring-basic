package com.sparta.work01.service;

import com.sparta.work01.domain.Notice;
import com.sparta.work01.domain.NoticeRepository;
import com.sparta.work01.domain.NoticeRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public Long update(Long id, NoticeRequestDto requestDto){
        Notice notice = noticeRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        notice.update(requestDto);
        return notice.getId();
    }

}
