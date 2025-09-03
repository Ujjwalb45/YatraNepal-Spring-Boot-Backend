package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "chad_parba")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Chad Parba (Nepali festivals) entity for managing festival information")
public class ChadParba {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the festival", example = "1")
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    @Schema(description = "Title of the festival", example = "Dashain")
    private String title;

    @NotBlank(message = "Description is required")
    @Column(columnDefinition = "TEXT", nullable = false)
    @Schema(description = "Description of the festival", example = "The biggest festival of Nepal")
    private String description;

    @NotNull(message = "Nepali month is required")
    @Enumerated(EnumType.STRING)
    @Column(name = "nepali_month", nullable = false)
    @Schema(description = "Nepali month when the festival occurs", example = "KARTIK")
    private NepaliMonth nepaliMonth;

    @NotNull(message = "Nepali day is required")
    @Min(value = 1, message = "Day must be at least 1")
    @Max(value = 32, message = "Day cannot exceed 32")
    @Column(name = "nepali_day", nullable = false)
    @Schema(description = "Day of the month in Nepali calendar", example = "10")
    private Integer nepaliDay;

    @NotBlank(message = "Category is required")
    @Column(nullable = false)
    @Schema(description = "Category of the festival", example = "Religious")
    private String category;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Festival creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Festival last update timestamp")
    private LocalDateTime updatedAt;

    // Enum for Nepali months
    public enum NepaliMonth {
        BAISAKH, JESTHA, ASHADH, SHRAWAN, BHADRA, ASHWIN,
        KARTIK, MANGSIR, POUSH, MAGH, FALGUN, CHAITRA
    }

    // Constructors
    public ChadParba() {}

    public ChadParba(String title, String description, NepaliMonth nepaliMonth, Integer nepaliDay, String category) {
        this.title = title;
        this.description = description;
        this.nepaliMonth = nepaliMonth;
        this.nepaliDay = nepaliDay;
        this.category = category;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public NepaliMonth getNepaliMonth() {
        return nepaliMonth;
    }

    public void setNepaliMonth(NepaliMonth nepaliMonth) {
        this.nepaliMonth = nepaliMonth;
    }

    public Integer getNepaliDay() {
        return nepaliDay;
    }

    public void setNepaliDay(Integer nepaliDay) {
        this.nepaliDay = nepaliDay;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
