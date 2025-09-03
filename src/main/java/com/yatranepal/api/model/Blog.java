package com.yatranepal.api.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "blogs")
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Blog entity representing blog posts")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the blog", example = "1")
    private Long id;

    @NotBlank(message = "Title is required")
    @Column(nullable = false)
    @Schema(description = "Title of the blog post", example = "Amazing Places in Nepal")
    private String title;

    @NotBlank(message = "Content is required")
    @Column(columnDefinition = "TEXT", nullable = false)
    @Schema(description = "Content of the blog post")
    private String content;

    @NotNull(message = "User ID is required")
    @Column(name = "user_id", nullable = false)
    @Schema(description = "ID of the user who created the blog", example = "1")
    private Long userId;

    @NotBlank(message = "Author name is required")
    @Column(nullable = false)
    @Schema(description = "Name of the blog author", example = "John Doe")
    private String name;

    @Column(name = "image_url")
    @Schema(description = "URL of the blog image", example = "https://example.com/image.jpg")
    private String img;

    @ElementCollection
    @CollectionTable(name = "blog_tags", joinColumns = @JoinColumn(name = "blog_id"))
    @Column(name = "tag")
    @Schema(description = "Tags associated with the blog")
    private List<String> tags;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    @Schema(description = "Blog creation timestamp")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    @Schema(description = "Blog last update timestamp")
    private LocalDateTime updatedAt;

    // Constructors
    public Blog() {}

    public Blog(String title, String content, Long userId, String name) {
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.name = name;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
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
