package com.sparta.work01.repository;

import com.sparta.work01.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
    List<Reply> findByNoticeId(Long noticeId);

    List<Reply> findAllByNoticeId(Long id);
}
