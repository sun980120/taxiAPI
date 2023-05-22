package com.example.taxi.repository;

import com.example.taxi.entity.DrivingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrivingRecordRepository extends JpaRepository<DrivingRecord, Long> {
}
