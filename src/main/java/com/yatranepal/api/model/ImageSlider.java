package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "image_sliders")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Image slider entity for homepage carousel images")
public class ImageSlider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the image slider", example = "1")
    private Long id;

    @NotBlank(message = "Name is required")
    @Column(nullable = false)
    @Schema(description = "Name/title of the slider image", example = "Beautiful Nepal Mountains")
    private String name;

    @NotNull(message = "Image type is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "image_type", nullable = false)
    @Schema(description = "Type of the image", example = "JPEG")
    private ImageType imageType;

    @NotBlank(message = "Image path is required")
    @Column(name = "image_path", nullable = false)
    @Schema(description = "Path/URL to the image file", example = "/images/slider/mountain1.jpg")
    private String imagePath;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Image slider creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Image slider last update timestamp")
    private LocalDateTime updatedAt;

    // Enum for image types
    public enum ImageType {
        JPEG("image/jpeg"),
        PNG("image/png");

        private final String mimeType;

        ImageType(String mimeType) {
            this.mimeType = mimeType;
        }

        public String getMimeType() {
            return mimeType;
        }
    }

    // Constructors
    public ImageSlider() {}

    public ImageSlider(String name, ImageType imageType, String imagePath) {
        this.name = name;
        this.imageType = imageType;
        this.imagePath = imagePath;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ImageType getImageType() {
        return imageType;
    }

    public void setImageType(ImageType imageType) {
        this.imageType = imageType;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
