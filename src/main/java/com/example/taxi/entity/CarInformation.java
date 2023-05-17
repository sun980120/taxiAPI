package com.example.taxi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String plateNumber;
    private String companyId;
    private String status;
    private String type;
    private String model;
    private String color;
    private LocalDateTime year;
    private String fuelType;
    private String missionType;
    private LocalDateTime registerDateTime;
    private String registerBy;
    private LocalDateTime updateDateTime;
    private String updateBy;
    @OneToMany(mappedBy = "carInformation", cascade = CascadeType.ALL)
    private List<DriverCar> driverCarList;
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
