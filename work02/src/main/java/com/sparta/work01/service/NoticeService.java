package com.sparta.work01.service;

import com.sparta.work01.model.Notice;
import com.sparta.work01.repository.NoticeRepository;
import com.sparta.work01.dto.NoticeRequestDto;
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
