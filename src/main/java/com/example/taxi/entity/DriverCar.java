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
public class DriverCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driveInformationId")
    private DriverInformation driverInformation;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "carInformationId")
    private CarInformation carInformation;
    private LocalDateTime registerDateTime;
    @PrePersist
    protected void onCreate() {
        registerDateTime = LocalDateTime.now();
    }

}
