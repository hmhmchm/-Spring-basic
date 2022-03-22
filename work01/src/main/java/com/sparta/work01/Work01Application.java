package com.sparta.work01;

import com.sparta.work01.domain.Notice;
import com.sparta.work01.domain.NoticeRepository;
import com.sparta.work01.service.NoticeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;

@EnableJpaAuditing // 변동이 있다면 jpa가 반응하도록
@SpringBootApplication
public class Work01Application {

    public static void main(String[] args) {
        SpringApplication.run(Work01Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(NoticeRepository noticeRepository, NoticeService noticeService){
        return (args) -> {
            noticeRepository.save(new Notice("현민초이","제목1", "내용1"));
            System.out.println("확인해보자~");

            List<Notice> noticeList = noticeRepository.findAll();
            for (int i=0; i<noticeList.size(); i++) {
                Notice notice = noticeList.get(i);
                System.out.println(notice.getId());
                System.out.println(notice.getUsername());
                System.out.println(notice.getTitle());
                System.out.println(notice.getContents());
            }
        };
    }
}
