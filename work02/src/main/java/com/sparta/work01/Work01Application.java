package com.sparta.work01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 변동이 있다면 jpa가 반응하도록
@SpringBootApplication
public class Work01Application {

    public static void main(String[] args) {
        SpringApplication.run(Work01Application.class, args);
    }


}
