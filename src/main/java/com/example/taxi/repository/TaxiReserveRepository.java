package com.example.taxi.repository;

import com.example.taxi.entity.TaxiReserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiReserveRepository extends JpaRepository<TaxiReserve, Long> {
}
