package com.example.taxi.repository;

import com.example.taxi.entity.CarInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarInformationRepository extends JpaRepository<CarInformation, Long> {
}
