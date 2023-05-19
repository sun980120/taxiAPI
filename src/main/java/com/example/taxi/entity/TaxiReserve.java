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
public class TaxiReserve {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userInformationId")
    private UserInformation userInformation;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driverCarId", referencedColumnName = "id")
    private DriverCar driverCar;
    private LocalDateTime registerDateTime;
    private LocalDateTime updateDateTime;
    private String arrLocation;
    private String depLocation;
    private String status;
    private LocalDateTime arrDateTime;
    private LocalDateTime depDateTime;
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
