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
public class DriverInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String birth;
    private String phone;
    private LocalDateTime registerDateTime;
    private LocalDateTime updateDateTime;
    private String companyId;
    @OneToMany(mappedBy = "driverInformation", cascade = CascadeType.ALL)
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
