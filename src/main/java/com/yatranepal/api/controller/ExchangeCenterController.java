package com.yatranepal.api.controller;

import com.yatranepal.api.model.ExchangeCenter;
import com.yatranepal.api.service.ExchangeCenterService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/exchange-centers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Exchange Center Management", description = "APIs for managing currency exchange centers")
public class ExchangeCenterController {

    @Autowired
    private ExchangeCenterService exchangeCenterService;

    @GetMapping
    @Operation(summary = "Get all exchange centers", description = "Retrieve all exchange centers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange centers retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> getAllExchangeCenters() {
        List<ExchangeCenter> centers = exchangeCenterService.getAllExchangeCenters();
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get exchange center by ID", description = "Retrieve a specific exchange center by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange center found"),
        @ApiResponse(responseCode = "404", description = "Exchange center not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExchangeCenter> getExchangeCenterById(@PathVariable Long id) {
        Optional<ExchangeCenter> center = exchangeCenterService.getExchangeCenterById(id);
        return center.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new exchange center", description = "Create a new exchange center")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange center created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExchangeCenter> createExchangeCenter(@Valid @RequestBody ExchangeCenter exchangeCenter) {
        ExchangeCenter createdCenter = exchangeCenterService.createExchangeCenter(exchangeCenter);
        return ResponseEntity.ok(createdCenter);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update exchange center", description = "Update an existing exchange center")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange center updated successfully"),
        @ApiResponse(responseCode = "404", description = "Exchange center not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExchangeCenter> updateExchangeCenter(@PathVariable Long id, @Valid @RequestBody ExchangeCenter exchangeCenter) {
        if (!exchangeCenterService.getExchangeCenterById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ExchangeCenter updatedCenter = exchangeCenterService.updateExchangeCenter(id, exchangeCenter);
        return ResponseEntity.ok(updatedCenter);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete exchange center", description = "Delete an exchange center by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange center deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Exchange center not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteExchangeCenter(@PathVariable Long id) {
        if (!exchangeCenterService.getExchangeCenterById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        exchangeCenterService.deleteExchangeCenter(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/owner/{ownerId}")
    @Operation(summary = "Get exchange centers by owner", description = "Retrieve all exchange centers owned by a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange centers retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> getExchangeCentersByOwnerId(@PathVariable Long ownerId) {
        List<ExchangeCenter> centers = exchangeCenterService.getExchangeCentersByOwnerId(ownerId);
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/active")
    @Operation(summary = "Get active exchange centers", description = "Retrieve all active exchange centers")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Active exchange centers retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> getActiveExchangeCenters() {
        List<ExchangeCenter> centers = exchangeCenterService.getActiveExchangeCenters();
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/status/{isActive}")
    @Operation(summary = "Get exchange centers by status", description = "Retrieve exchange centers by active/inactive status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange centers retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> getExchangeCentersByStatus(@PathVariable Boolean isActive) {
        List<ExchangeCenter> centers = exchangeCenterService.getExchangeCentersByStatus(isActive);
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/search/name")
    @Operation(summary = "Search exchange centers by name", description = "Search exchange centers by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> searchExchangeCentersByName(@RequestParam String name) {
        List<ExchangeCenter> centers = exchangeCenterService.searchExchangeCentersByName(name);
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/search/address")
    @Operation(summary = "Search exchange centers by address", description = "Search exchange centers by address")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> searchExchangeCentersByAddress(@RequestParam String address) {
        List<ExchangeCenter> centers = exchangeCenterService.searchExchangeCentersByAddress(address);
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/search/service")
    @Operation(summary = "Search exchange centers by service", description = "Search exchange centers by service offered")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> searchExchangeCentersByService(@RequestParam String service) {
        List<ExchangeCenter> centers = exchangeCenterService.searchExchangeCentersByService(service);
        return ResponseEntity.ok(centers);
    }

    @GetMapping("/nearby")
    @Operation(summary = "Get nearby exchange centers", description = "Find exchange centers within specified radius")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Nearby exchange centers retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ExchangeCenter>> getExchangeCentersNearLocation(
            @RequestParam BigDecimal lat,
            @RequestParam BigDecimal lng,
            @RequestParam(defaultValue = "10.0") Double radius) {
        List<ExchangeCenter> centers = exchangeCenterService.getExchangeCentersNearLocation(lat, lng, radius);
        return ResponseEntity.ok(centers);
    }

    @PutMapping("/{id}/activate")
    @Operation(summary = "Activate exchange center", description = "Activate an exchange center")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange center activated successfully"),
        @ApiResponse(responseCode = "404", description = "Exchange center not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExchangeCenter> activateExchangeCenter(@PathVariable Long id) {
        ExchangeCenter center = exchangeCenterService.activateExchangeCenter(id);
        if (center != null) {
            return ResponseEntity.ok(center);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate exchange center", description = "Deactivate an exchange center")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Exchange center deactivated successfully"),
        @ApiResponse(responseCode = "404", description = "Exchange center not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ExchangeCenter> deactivateExchangeCenter(@PathVariable Long id) {
        ExchangeCenter center = exchangeCenterService.deactivateExchangeCenter(id);
        if (center != null) {
            return ResponseEntity.ok(center);
        }
        return ResponseEntity.notFound().build();
    }
}
