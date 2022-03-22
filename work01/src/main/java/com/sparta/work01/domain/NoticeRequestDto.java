package com.sparta.work01.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class NoticeRequestDto {
    private String username;
    private String title;
    private String contents;
}
