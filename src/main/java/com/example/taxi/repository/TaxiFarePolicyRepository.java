package com.example.taxi.repository;

import com.example.taxi.entity.TaxiFarePolicy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaxiFarePolicyRepository extends JpaRepository<TaxiFarePolicy, Long> {
}
