package com.yatranepal.api.controller;

import com.yatranepal.api.model.ChadParba;
import com.yatranepal.api.service.ChadParbaService;
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
@RequestMapping("/api/chad-parba")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Chad Parba Management", description = "APIs for managing Nepali festivals and celebrations")
public class ChadParbaController {

    @Autowired
    private ChadParbaService chadParbaService;

    @GetMapping
    @Operation(summary = "Get all festivals", description = "Retrieve all Nepali festivals ordered by date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festivals retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ChadParba>> getAllChadParba() {
        List<ChadParba> festivals = chadParbaService.getAllChadParba();
        return ResponseEntity.ok(festivals);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get festival by ID", description = "Retrieve a specific festival by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festival found"),
        @ApiResponse(responseCode = "404", description = "Festival not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ChadParba> getChadParbaById(@PathVariable Long id) {
        Optional<ChadParba> festival = chadParbaService.getChadParbaById(id);
        return festival.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new festival", description = "Add a new Nepali festival")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festival created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ChadParba> createChadParba(@Valid @RequestBody ChadParba chadParba) {
        ChadParba createdFestival = chadParbaService.createChadParba(chadParba);
        return ResponseEntity.ok(createdFestival);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update festival", description = "Update an existing festival")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festival updated successfully"),
        @ApiResponse(responseCode = "404", description = "Festival not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ChadParba> updateChadParba(@PathVariable Long id, @Valid @RequestBody ChadParba chadParba) {
        if (!chadParbaService.getChadParbaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ChadParba updatedFestival = chadParbaService.updateChadParba(id, chadParba);
        return ResponseEntity.ok(updatedFestival);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete festival", description = "Delete a festival by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festival deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Festival not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteChadParba(@PathVariable Long id) {
        if (!chadParbaService.getChadParbaById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        chadParbaService.deleteChadParba(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/month/{nepaliMonth}")
    @Operation(summary = "Get festivals by month", description = "Retrieve festivals by Nepali month")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festivals retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ChadParba>> getChadParbaByMonth(@PathVariable ChadParba.NepaliMonth nepaliMonth) {
        List<ChadParba> festivals = chadParbaService.getChadParbaByMonth(nepaliMonth);
        return ResponseEntity.ok(festivals);
    }

    @GetMapping("/category/{category}")
    @Operation(summary = "Get festivals by category", description = "Retrieve festivals by category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festivals retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ChadParba>> getChadParbaByCategory(@PathVariable String category) {
        List<ChadParba> festivals = chadParbaService.getChadParbaByCategory(category);
        return ResponseEntity.ok(festivals);
    }

    @GetMapping("/search")
    @Operation(summary = "Search festivals by title", description = "Search festivals by title")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ChadParba>> searchChadParbaByTitle(@RequestParam String title) {
        List<ChadParba> festivals = chadParbaService.searchChadParbaByTitle(title);
        return ResponseEntity.ok(festivals);
    }

    @GetMapping("/date/{nepaliMonth}/{nepaliDay}")
    @Operation(summary = "Get festivals by specific date", description = "Retrieve festivals by specific Nepali date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Festivals retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ChadParba>> getChadParbaByDate(
            @PathVariable ChadParba.NepaliMonth nepaliMonth,
            @PathVariable Integer nepaliDay) {
        List<ChadParba> festivals = chadParbaService.getChadParbaByDate(nepaliMonth, nepaliDay);
        return ResponseEntity.ok(festivals);
    }
}
