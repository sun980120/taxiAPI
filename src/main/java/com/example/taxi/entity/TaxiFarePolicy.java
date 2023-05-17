package com.example.taxi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaxiFarePolicy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int baseCharge;
    private int baseChargeNight;
    private int baseDistance;
    private int chargeUnit;
    private int distanceCharge;
    private int timeCharge;
    private int nightStart;
    private int nightEnd;
    private int nightPremium;
    private int outsidePremium;
    private LocalDateTime registerDateTime;
    private LocalDateTime updateDateTime;
    private String registerBy;
    private int lowSpeed;
    private int callCharge;
    private int callChargeNight;
    @PrePersist
    protected void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        registerDateTime = now;
        updateDateTime = now;
    }

    @PreUpdate
    protected void onUpdate() {
        updateDateTime = LocalDateTime.now();
    }
}
