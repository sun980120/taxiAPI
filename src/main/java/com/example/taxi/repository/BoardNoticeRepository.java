package com.example.taxi.repository;

import com.example.taxi.entity.BoardNotice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardNoticeRepository extends JpaRepository<BoardNotice, Long> {
}
