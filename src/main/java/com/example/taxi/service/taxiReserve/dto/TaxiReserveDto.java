package com.example.taxi.service.taxiReserve.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaxiReserveDto {
    private Long userInformationId;
    private LocationDto arrLocation;
    private LocationDto depLocation;
    private String depDateTime;
}