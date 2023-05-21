package com.example.taxi.service.common;

import com.example.taxi.service.taxiReserve.dto.TaxiReserveDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;

@Service
@Transactional
public class ArrivalTimeService {
    private static final String KAKAO_API_URL = "https://apis-navi.kakaomobility.com/v1/directions";
    private static final String API_KEY = "e0521107d547145dfca2033fe58e4233";

    private final RestTemplate restTemplate;
    private final WebClient webClient;

    public ArrivalTimeService() {
        this.restTemplate = new RestTemplate();
        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector())
                .baseUrl(KAKAO_API_URL)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "KakaoAK " + API_KEY)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<JSONObject> getArrivalTimeWebClient(TaxiReserveDto taxiReserveDto) {

        // API 호출
        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .queryParam("origin", taxiReserveDto.getDepLocation().getLongitude() + "," + taxiReserveDto.getDepLocation().getLatitude())
                        .queryParam("destination", taxiReserveDto.getArrLocation().getLongitude() + "," + taxiReserveDto.getArrLocation().getLatitude())
                        .queryParam("car_type", 1)
                        .queryParam("car_fuel", "GASOLINE")
                        .queryParam("car_hipass", true)
                        .queryParam("summary", true)
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .map(JSONObject::new);
    }
    public int getArrivalTimeRestTemplate(TaxiReserveDto taxiReserveDto) {
        String url = KAKAO_API_URL + "?origin=" + taxiReserveDto.getDepLocation().getLongitude() + "," + taxiReserveDto.getDepLocation().getLatitude() +
                "&destination=" + taxiReserveDto.getArrLocation().getLongitude() + "," + taxiReserveDto.getArrLocation().getLatitude() +
                "&car_type=1&car_fuel=GASOLINE&car_hipass=true&summary=true";

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + API_KEY);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            JSONObject jsonObject = new JSONObject(response.getBody());
            JSONArray routes = jsonObject.getJSONArray("routes");
            if (routes.length() > 0) {
                JSONObject route = routes.getJSONObject(0);
                JSONObject summary = route.getJSONObject("summary");
                int duration = summary.getInt("duration");
                return duration;
            }
            throw new RuntimeException("Duration 호출 중 오류 발생");
        } else {
            throw new RuntimeException("API 호출 중 오류 발생: " + response.getStatusCode());
        }
    }

    // 이하 생략
}
