package com.yatranepal.api.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FlightService {

    private static final String API_URL = "http://api.aviationstack.com/v1/flights";
    private static final String API_KEY = "d4421700d5152c641c7dd163532aae6d"; // 🔑 Replace with your key

    private final RestTemplate restTemplate = new RestTemplate();

    // ✅ Get all flights (real-time)
    public Map getAllFlights() {
        String url = API_URL + "?access_key=" + API_KEY;

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        return response.getBody();
    }
}
