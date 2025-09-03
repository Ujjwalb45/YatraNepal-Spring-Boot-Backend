package com.yatranepal.api.controller;

import com.yatranepal.api.model.TouristGuide;
import com.yatranepal.api.service.TouristGuideService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tourist-guides")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Tourist Guide Management", description = "APIs for managing tourist guides")
public class TouristGuideController {

    @Autowired
    private TouristGuideService touristGuideService;

    @GetMapping
    @Operation(summary = "Get all tourist guides", description = "Retrieve all registered tourist guides")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guides retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TouristGuide>> getAllTouristGuides() {
        List<TouristGuide> guides = touristGuideService.getAllTouristGuides();
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get tourist guide by ID", description = "Retrieve a specific tourist guide by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guide found"),
        @ApiResponse(responseCode = "404", description = "Tourist guide not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TouristGuide> getTouristGuideById(@PathVariable Long id) {
        Optional<TouristGuide> guide = touristGuideService.getTouristGuideById(id);
        return guide.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new tourist guide", description = "Register a new tourist guide")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guide created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TouristGuide> createTouristGuide(@Valid @RequestBody TouristGuide touristGuide) {
        TouristGuide createdGuide = touristGuideService.createTouristGuide(touristGuide);
        return ResponseEntity.ok(createdGuide);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update tourist guide", description = "Update an existing tourist guide")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guide updated successfully"),
        @ApiResponse(responseCode = "404", description = "Tourist guide not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TouristGuide> updateTouristGuide(@PathVariable Long id, @Valid @RequestBody TouristGuide touristGuide) {
        if (!touristGuideService.getTouristGuideById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        TouristGuide updatedGuide = touristGuideService.updateTouristGuide(id, touristGuide);
        return ResponseEntity.ok(updatedGuide);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete tourist guide", description = "Delete a tourist guide by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guide deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Tourist guide not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteTouristGuide(@PathVariable Long id) {
        if (!touristGuideService.getTouristGuideById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        touristGuideService.deleteTouristGuide(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get tourist guide by user ID", description = "Retrieve tourist guide profile by user ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guide found"),
        @ApiResponse(responseCode = "404", description = "Tourist guide not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TouristGuide> getTouristGuideByUserId(@PathVariable Long userId) {
        Optional<TouristGuide> guide = touristGuideService.getTouristGuideByUserId(userId);
        return guide.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get tourist guide by email", description = "Retrieve tourist guide by email address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guide found"),
        @ApiResponse(responseCode = "404", description = "Tourist guide not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TouristGuide> getTouristGuideByEmail(@PathVariable String email) {
        Optional<TouristGuide> guide = touristGuideService.getTouristGuideByEmail(email);
        return guide.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/license/{licenseNumber}")
    @Operation(summary = "Get tourist guide by license", description = "Retrieve tourist guide by license number")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guide found"),
        @ApiResponse(responseCode = "404", description = "Tourist guide not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<TouristGuide> getTouristGuideByLicenseNumber(@PathVariable String licenseNumber) {
        Optional<TouristGuide> guide = touristGuideService.getTouristGuideByLicenseNumber(licenseNumber);
        return guide.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/location/{location}")
    @Operation(summary = "Get tourist guides by location", description = "Retrieve tourist guides by location")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guides retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TouristGuide>> getTouristGuidesByLocation(@PathVariable String location) {
        List<TouristGuide> guides = touristGuideService.getTouristGuidesByLocation(location);
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/language/{language}")
    @Operation(summary = "Get tourist guides by language", description = "Retrieve tourist guides by spoken language")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guides retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TouristGuide>> getTouristGuidesByLanguage(@PathVariable String language) {
        List<TouristGuide> guides = touristGuideService.getTouristGuidesByLanguage(language);
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/availability/{availability}")
    @Operation(summary = "Get tourist guides by availability", description = "Retrieve tourist guides by availability status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guides retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TouristGuide>> getTouristGuidesByAvailability(@PathVariable String availability) {
        List<TouristGuide> guides = touristGuideService.getTouristGuidesByAvailability(availability);
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/experience/{minExperience}")
    @Operation(summary = "Get tourist guides by minimum experience", description = "Retrieve tourist guides with minimum years of experience")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guides retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TouristGuide>> getTouristGuidesByMinimumExperience(@PathVariable Integer minExperience) {
        List<TouristGuide> guides = touristGuideService.getTouristGuidesByMinimumExperience(minExperience);
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get tourist guides by category", description = "Retrieve tourist guides by tour category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Tourist guides retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TouristGuide>> getTouristGuidesByCategory(@PathVariable String category) {
        List<TouristGuide> guides = touristGuideService.getTouristGuidesByCategory(category);
        return ResponseEntity.ok(guides);
    }

    @GetMapping("/search")
    @Operation(summary = "Search tourist guides by name", description = "Search tourist guides by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<TouristGuide>> searchTouristGuidesByName(@RequestParam String name) {
        List<TouristGuide> guides = touristGuideService.searchTouristGuidesByName(name);
        return ResponseEntity.ok(guides);
    }
}
