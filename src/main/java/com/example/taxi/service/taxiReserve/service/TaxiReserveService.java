package com.example.taxi.service.taxiReserve.service;

import com.example.taxi.entity.TaxiReserve;
import com.example.taxi.service.taxiReserve.dto.TaxiReserveDto;

public interface TaxiReserveService {
    TaxiReserve saveTaxiReserving(TaxiReserveDto taxiReserve);
}
