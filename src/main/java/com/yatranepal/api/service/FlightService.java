package com.yatranepal.api.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightService {

    private static final String OPEN_SKY_URL
            = "https://opensky-network.org/api/states/all"
            + "?lamin=26.347&lomin=80.058&lamax=30.447&lomax=88.201"; // Nepal bounding box

    private final RestTemplate restTemplate = new RestTemplate();

    public Map getNepalFlights() {
        ResponseEntity<Map> response = restTemplate.getForEntity(OPEN_SKY_URL, Map.class);
        return response.getBody();
    }
}
