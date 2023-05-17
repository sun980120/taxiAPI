package com.example.taxi.repository;

import com.example.taxi.entity.DriverInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverInformationRepository extends JpaRepository<DriverInformation, Long> {
}
