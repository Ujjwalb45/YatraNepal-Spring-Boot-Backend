package com.yatranepal.api.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yatranepal.api.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    // ✅ Endpoint to get all real-time flights
    @GetMapping("/all")
    public Map getAllFlights() {
        return flightService.getAllFlights();
    }

}
