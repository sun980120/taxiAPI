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
public class UserInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userEmail;
    @Column(length = 512)
    private String userPassword;
    private String phone;
    private String nickname;
    private String language;
    private LocalDateTime registerDateTime;
    private LocalDateTime updateDateTime;
    @OneToMany(mappedBy = "userInformation", cascade = CascadeType.ALL)
    private List<TaxiReserve> taxiReserveList;
    @OneToMany(mappedBy = "userInformation", cascade = CascadeType.ALL)
    private List<DrivingRecord> drivingRecordList;
    @OneToMany(mappedBy = "userInformation", cascade = CascadeType.ALL)
    private List<UserPayment> userPaymentList;

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
