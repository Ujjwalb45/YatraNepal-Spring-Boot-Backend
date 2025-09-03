package com.yatranepal.api.controller;

import com.yatranepal.api.model.Blog;
import com.yatranepal.api.service.BlogService;
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
@RequestMapping("/api/blogs")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@Tag(name = "Blog Management", description = "APIs for managing blog posts")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    @Operation(summary = "Get all blogs", description = "Retrieve all blog posts ordered by creation date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blogs retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get blog by ID", description = "Retrieve a specific blog by its ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog found"),
        @ApiResponse(responseCode = "404", description = "Blog not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Optional<Blog> blog = blogService.getBlogById(id);
        return blog.map(ResponseEntity::ok)
                   .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create new blog", description = "Create a new blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Blog> createBlog(@Valid @RequestBody Blog blog) {
        Blog createdBlog = blogService.createBlog(blog);
        return ResponseEntity.ok(createdBlog);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update blog", description = "Update an existing blog post")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog updated successfully"),
        @ApiResponse(responseCode = "404", description = "Blog not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @Valid @RequestBody Blog blog) {
        if (!blogService.getBlogById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Blog updatedBlog = blogService.updateBlog(id, blog);
        return ResponseEntity.ok(updatedBlog);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete blog", description = "Delete a blog post by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blog deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Blog not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        if (!blogService.getBlogById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        blogService.deleteBlog(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get blogs by user", description = "Retrieve all blogs by a specific user")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blogs retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Blog>> getBlogsByUserId(@PathVariable Long userId) {
        List<Blog> blogs = blogService.getBlogsByUserId(userId);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/search")
    @Operation(summary = "Search blogs", description = "Search blogs by title")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Blog>> searchBlogsByTitle(@RequestParam String title) {
        List<Blog> blogs = blogService.searchBlogsByTitle(title);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/search/keyword")
    @Operation(summary = "Search blogs by keyword", description = "Search blogs by keyword in title or content")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Blog>> searchBlogsByKeyword(@RequestParam String keyword) {
        List<Blog> blogs = blogService.searchBlogsByKeyword(keyword);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/tag/{tag}")
    @Operation(summary = "Get blogs by tag", description = "Retrieve blogs by a specific tag")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Blogs retrieved successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Blog>> getBlogsByTag(@PathVariable String tag) {
        List<Blog> blogs = blogService.getBlogsByTag(tag);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/author")
    @Operation(summary = "Search blogs by author", description = "Search blogs by author name")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Search completed successfully"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<Blog>> getBlogsByAuthor(@RequestParam String author) {
        List<Blog> blogs = blogService.getBlogsByAuthor(author);
        return ResponseEntity.ok(blogs);
    }
}
