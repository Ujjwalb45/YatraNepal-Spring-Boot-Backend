package com.yatranepal.api.controller;

import com.yatranepal.api.model.ImageSlider;
import com.yatranepal.api.service.ImageSliderService;
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
@RequestMapping("/api/image-sliders")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Image Slider Management", description = "APIs for managing homepage carousel images")
public class ImageSliderController {

    @Autowired
    private ImageSliderService imageSliderService;

    @GetMapping
    @Operation(summary = "Get all image sliders", description = "Retrieve all image sliders ordered by creation date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Image sliders retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ImageSlider>> getAllImageSliders() {
        List<ImageSlider> sliders = imageSliderService.getAllImageSliders();
        return ResponseEntity.ok(sliders);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get image slider by ID", description = "Retrieve a specific image slider by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Image slider found"),
        @ApiResponse(responseCode = "404", description = "Image slider not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ImageSlider> getImageSliderById(@PathVariable Long id) {
        Optional<ImageSlider> slider = imageSliderService.getImageSliderById(id);
        return slider.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new image slider", description = "Upload a new image for the slider")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Image slider created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ImageSlider> createImageSlider(@Valid @RequestBody ImageSlider imageSlider) {
        ImageSlider createdSlider = imageSliderService.createImageSlider(imageSlider);
        return ResponseEntity.ok(createdSlider);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update image slider", description = "Update an existing image slider")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Image slider updated successfully"),
        @ApiResponse(responseCode = "404", description = "Image slider not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<ImageSlider> updateImageSlider(@PathVariable Long id, @Valid @RequestBody ImageSlider imageSlider) {
        if (!imageSliderService.getImageSliderById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ImageSlider updatedSlider = imageSliderService.updateImageSlider(id, imageSlider);
        return ResponseEntity.ok(updatedSlider);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete image slider", description = "Delete an image slider by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Image slider deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Image slider not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteImageSlider(@PathVariable Long id) {
        if (!imageSliderService.getImageSliderById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        imageSliderService.deleteImageSlider(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search image sliders by name", description = "Search image sliders by name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ImageSlider>> searchImageSlidersByName(@RequestParam String name) {
        List<ImageSlider> sliders = imageSliderService.searchImageSlidersByName(name);
        return ResponseEntity.ok(sliders);
    }

    @GetMapping("/type/{imageType}")
    @Operation(summary = "Get image sliders by type", description = "Retrieve image sliders by image type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Image sliders retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<ImageSlider>> getImageSlidersByType(@PathVariable ImageSlider.ImageType imageType) {
        List<ImageSlider> sliders = imageSliderService.getImageSlidersByType(imageType);
        return ResponseEntity.ok(sliders);
    }
}
