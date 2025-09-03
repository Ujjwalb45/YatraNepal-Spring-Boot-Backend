package com.yatranepal.api.controller;

import com.yatranepal.api.model.Hotel;
import com.yatranepal.api.service.HotelService;
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
@RequestMapping("/hotels")
@Tag(name = "Hotels", description = "Hotel management APIs")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping
    @Operation(summary = "Get all hotels", description = "Retrieve all hotels with optional filtering")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotels retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Hotel>> getAllHotels(
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Boolean featured) {
        
        List<Hotel> hotels;
        
        if (featured != null && featured) {
            hotels = hotelService.getFeaturedHotels();
        } else if (city != null && type != null) {
            hotels = hotelService.getHotelsByCityAndType(city, type);
        } else if (city != null) {
            hotels = hotelService.getHotelsByCity(city);
        } else if (type != null) {
            hotels = hotelService.getHotelsByType(type);
        } else if (minPrice != null && maxPrice != null) {
            hotels = hotelService.getHotelsByPriceRange(minPrice, maxPrice);
        } else {
            hotels = hotelService.getAllHotels();
        }
        
        return ResponseEntity.ok(hotels);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get hotel by ID", description = "Retrieve a specific hotel by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel found"),
        @ApiResponse(responseCode = "404", description = "Hotel not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Hotel> getHotelById(@PathVariable Long id) {
        Optional<Hotel> hotel = hotelService.getHotelById(id);
        return hotel.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new hotel", description = "Create a new hotel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Hotel> createHotel(@Valid @RequestBody Hotel hotel) {
        Hotel createdHotel = hotelService.createHotel(hotel);
        return ResponseEntity.ok(createdHotel);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update hotel", description = "Update an existing hotel")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel updated successfully"),
        @ApiResponse(responseCode = "404", description = "Hotel not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Hotel> updateHotel(@PathVariable Long id, @Valid @RequestBody Hotel hotel) {
        if (!hotelService.getHotelById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Hotel updatedHotel = hotelService.updateHotel(id, hotel);
        return ResponseEntity.ok(updatedHotel);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete hotel", description = "Delete a hotel by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Hotel not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        if (!hotelService.getHotelById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        hotelService.deleteHotel(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search hotels", description = "Search hotels by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Hotel>> searchHotels(@RequestParam String name) {
        List<Hotel> hotels = hotelService.searchHotelsByName(name);
        return ResponseEntity.ok(hotels);
    }
}
