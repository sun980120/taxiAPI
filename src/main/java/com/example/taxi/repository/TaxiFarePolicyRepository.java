package com.example.taxi.repository;

import com.example.taxi.entity.TaxiFarePolicy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiFarePolicyRepository extends JpaRepository<TaxiFarePolicy, Long> {
}
