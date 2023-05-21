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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
            arrivalTimeService.getArrivalTimeWebClient(taxiReserve).subscribe(result -> {
                System.out.println("도착 예상 시간: " + result);
//                System.out.println();
                }, error -> {
                System.out.println("API 호출 중 오류 발생: " + error.getMessage());
            });
            int t = arrivalTimeService.getArrivalTimeRestTemplate(taxiReserve);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(taxiReserve.getDepDateTime(), formatter);
//            TaxiReserve result = TaxiReserve.builder();
            System.out.println(t);

            TaxiReserve saveTaxiReserve = TaxiReserve.builder()
                    .arrLocation(taxiReserve.getArrLocation().toString())
                    .depLocation(taxiReserve.getDepLocation().toString())
                    .depDateTime(dateTime)
                    .arrDateTime(dateTime.plusSeconds(t))
                    .userInformation(userInformation)
                    .build();
            taxiReserveRepository.save(saveTaxiReserve);

            return saveTaxiReserve;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
