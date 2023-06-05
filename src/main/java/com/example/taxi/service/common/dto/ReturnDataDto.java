package com.example.taxi.service.common.dto;

import com.example.taxi.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReturnDataDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BoardNotice boardNotice;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private CarInformation carInformation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DriverCar driverCar;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DriverInformation driverInformation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private DrivingRecord drivingRecord;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private TaxiReserve taxiReserve;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserInformation userInformation;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserPayment userPayment;
}
