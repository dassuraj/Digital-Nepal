package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.Blog;
import com.security.webtech.digitalnepalfullstackcrud.service.BlogService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class BlogControllerTest {

    @Mock
    private BlogService blogService;

    @InjectMocks
    private BlogController blogController;

    private Blog blog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Sample Blog");
        blog.setContent("This is a sample blog content.");
    }

    @Test
    void createBlog() {
        when(blogService.save(any(Blog.class))).thenReturn(blog);

        ResponseEntity<Blog> response = blogController.createBlog(blog);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Blog", response.getBody().getTitle());
        verify(blogService, times(1)).save(any(Blog.class));
    }

    @Test
    void getAllBlogs() {
        when(blogService.findAll()).thenReturn(Arrays.asList(blog));

        ResponseEntity<List<Blog>> response = blogController.getAllBlogs();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(blogService, times(1)).findAll();
    }

    @Test
    void getBlogById() {
        when(blogService.findById(anyLong())).thenReturn(blog);

        ResponseEntity<Blog> response = blogController.getBlogById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Blog", response.getBody().getTitle());
        verify(blogService, times(1)).findById(anyLong());
    }

    @Test
    void getBlogById_NotFound() {
        when(blogService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Blog> response = blogController.getBlogById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(blogService, times(1)).findById(anyLong());
    }

    @Test
    void updateBlog() {
        when(blogService.findById(anyLong())).thenReturn(blog);
        when(blogService.save(any(Blog.class))).thenReturn(blog);

        ResponseEntity<Blog> response = blogController.updateBlog(1L, blog);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Sample Blog", response.getBody().getTitle());
        verify(blogService, times(1)).findById(anyLong());
        verify(blogService, times(1)).save(any(Blog.class));
    }

    @Test
    void updateBlog_NotFound() {
        when(blogService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Blog> response = blogController.updateBlog(1L, blog);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(blogService, times(1)).findById(anyLong());
    }

    @Test
    void deleteBlog() {
        when(blogService.findById(anyLong())).thenReturn(blog);
        doNothing().when(blogService).delete(anyLong());

        ResponseEntity<Void> response = blogController.deleteBlog(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(blogService, times(1)).findById(anyLong());
        verify(blogService, times(1)).delete(anyLong());
    }

    @Test
    void deleteBlog_NotFound() {
        when(blogService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Void> response = blogController.deleteBlog(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(blogService, times(1)).findById(anyLong());
    }
}
