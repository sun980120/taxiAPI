package com.example.taxi.service.taxiReserve.service;

import com.example.taxi.entity.TaxiReserve;
import com.example.taxi.entity.UserInformation;
import com.example.taxi.repository.TaxiReserveRepository;
import com.example.taxi.repository.UserInformationRepository;
import com.example.taxi.service.common.ArrivalTimeService;
import com.example.taxi.service.taxiReserve.dto.TaxiReserveDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaxiReserveServiceImpl implements TaxiReserveService{
    private final TaxiReserveRepository taxiReserveRepository;
    private final UserInformationRepository userInformationRepository;
    private final ArrivalTimeService arrivalTimeService;
    @Override
    public TaxiReserve saveTaxiReserving(TaxiReserveDto taxiReserve) {
        try {
            UserInformation userInformation = userInformationRepository.findById(taxiReserve.getUserInformationId()).get();
            arrivalTimeService.getArrivalTime(taxiReserve).subscribe(result -> {
                System.out.println("도착 예상 시간: " + result.);
            }, error -> {
                System.out.println("API 호출 중 오류 발생: " + error.getMessage());
            });
//            TaxiReserve result = TaxiReserve.builder();
//            System.out.println(t);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
