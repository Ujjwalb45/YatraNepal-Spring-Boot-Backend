package com.yatranepal.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yatranepal.api.service.FlightService;

@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping("/flights/nepal")
    public Map getNepalFlights() {
        return flightService.getNepalFlights();
    }

}
