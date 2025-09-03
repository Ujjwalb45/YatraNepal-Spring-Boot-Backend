package com.yatranepal.api.controller;

import com.yatranepal.api.model.Place;
import com.yatranepal.api.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/place")
@Tag(name = "Places", description = "Tourist place management APIs")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @GetMapping
    @Operation(summary = "Get all places", description = "Retrieve all tourist places with optional filtering")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Places retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Place>> getAllPlaces(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String category) {
        
        List<Place> places;
        
        if (city != null && category != null) {
            places = placeService.getPlacesByCityAndCategory(city, category);
        } else if (city != null) {
            places = placeService.getPlacesByCity(city);
        } else if (category != null) {
            places = placeService.getPlacesByCategory(category);
        } else {
            places = placeService.getAllPlaces();
        }
        
        return ResponseEntity.ok(places);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get place by ID", description = "Retrieve a specific place by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Place found"),
        @ApiResponse(responseCode = "404", description = "Place not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Place> getPlaceById(@PathVariable Long id) {
        Optional<Place> place = placeService.getPlaceById(id);
        return place.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new place", description = "Create a new tourist place")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Place created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Place> createPlace(@Valid @RequestBody Place place) {
        Place createdPlace = placeService.createPlace(place);
        return ResponseEntity.ok(createdPlace);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update place", description = "Update an existing place")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Place updated successfully"),
        @ApiResponse(responseCode = "404", description = "Place not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Place> updatePlace(@PathVariable Long id, @Valid @RequestBody Place place) {
        if (!placeService.getPlaceById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Place updatedPlace = placeService.updatePlace(id, place);
        return ResponseEntity.ok(updatedPlace);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete place", description = "Delete a place by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Place deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Place not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deletePlace(@PathVariable Long id) {
        if (!placeService.getPlaceById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        placeService.deletePlace(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search places", description = "Search places by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Place>> searchPlaces(@RequestParam String name) {
        List<Place> places = placeService.searchPlacesByName(name);
        return ResponseEntity.ok(places);
    }

    @GetMapping("/nearby")
    @Operation(summary = "Get nearby places", description = "Get places near a specific location")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Nearby places retrieved successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid coordinates"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Place>> getNearbyPlaces(
            @RequestParam Double longitude,
            @RequestParam Double latitude,
            @RequestParam(defaultValue = "10000") Double maxDistance) {
        
        List<Place> places = placeService.getPlacesNearLocation(longitude, latitude, maxDistance);
        return ResponseEntity.ok(places);
    }
}
