package com.example.taxi.service.taxiReserve;

import com.example.taxi.entity.TaxiReserve;
import com.example.taxi.service.taxiReserve.dto.TaxiReserveDto;
import com.example.taxi.service.taxiReserve.service.TaxiReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class TaxiReserveGraphql {

    private final TaxiReserveService taxiReserveService;

    @SchemaMapping(typeName = "Mutation", value = "saveTaxiReserving")
    public TaxiReserve saveTaxiReserving(@Argument TaxiReserveDto taxiReserve) {
        return taxiReserveService.saveTaxiReserving(taxiReserve);
    }
}
