package com.example.taxi.repository;

import com.example.taxi.entity.DriverCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverCarRepository extends JpaRepository<DriverCar, Long> {
}
