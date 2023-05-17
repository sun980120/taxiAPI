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
public class DrivingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driverCarId", referencedColumnName = "id")
    private DriverCar driverCar;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInformationId")
    private UserInformation userInformation;
    private String depLocation;  // 출발/도착 위치?
    private String arrLocation;  // 출발/도착 위치?
    private int charge;
    private LocalDateTime depTime; // 출발/도착 시간?
    private LocalDateTime arrTime; // 출발/도착 시간?
}
