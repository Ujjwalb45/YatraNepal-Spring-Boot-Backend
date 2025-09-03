package com.yatranepal.api.service;

import com.yatranepal.api.model.Blog;
import com.yatranepal.api.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<Blog> getAllBlogs() {
        return blogRepository.findAllOrderByCreatedAtDesc();
    }

    public Optional<Blog> getBlogById(Long id) {
        return blogRepository.findById(id);
    }

    public Blog createBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public Blog updateBlog(Long id, Blog blog) {
        blog.setId(id);
        return blogRepository.save(blog);
    }

    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    public List<Blog> getBlogsByUserId(Long userId) {
        return blogRepository.findByUserId(userId);
    }

    public List<Blog> searchBlogsByTitle(String title) {
        return blogRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Blog> searchBlogsByKeyword(String keyword) {
        return blogRepository.findByContentOrTitleContaining(keyword);
    }

    public List<Blog> getBlogsByTag(String tag) {
        return blogRepository.findByTag(tag);
    }

    public List<Blog> getBlogsByAuthor(String authorName) {
        return blogRepository.findByNameContainingIgnoreCase(authorName);
    }
}
