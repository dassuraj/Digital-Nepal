package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Blog;
import com.security.webtech.digitalnepalfullstackcrud.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BlogServiceImplTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogServiceImpl blogService;

    private Blog blog;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        blog = new Blog();
        blog.setId(1L);
        blog.setTitle("Tech Blog");
        blog.setContent("This is a tech blog content.");
    }

    @Test
    void findAll() {
        List<Blog> blogList = new ArrayList<>();
        blogList.add(blog);
        when(blogRepository.findAll()).thenReturn(blogList);

        List<Blog> result = blogService.findAll();
        assertEquals(1, result.size());
        verify(blogRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));

        Blog result = blogService.findById(1L);
        assertNotNull(result);
        assertEquals("Tech Blog", result.getTitle());
        verify(blogRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(blogRepository.save(any(Blog.class))).thenReturn(blog);

        Blog result = blogService.save(blog);
        assertNotNull(result);
        assertEquals("Tech Blog", result.getTitle());
        verify(blogRepository, times(1)).save(blog);
    }

    @Test
    void update() {
        when(blogRepository.existsById(1L)).thenReturn(true);
        when(blogRepository.findById(1L)).thenReturn(Optional.of(blog));
        when(blogRepository.save(blog)).thenReturn(blog);

        Blog updatedBlog = new Blog();
        updatedBlog.setTitle("Updated Blog Title");
        updatedBlog.setContent("Updated blog content");

        Blog result = blogService.update(updatedBlog, 1L);
        assertEquals("Updated Blog Title", result.getTitle());
        assertEquals("Updated blog content", result.getContent());
        verify(blogRepository, times(1)).save(blog);
    }

    @Test
    void delete() {
        doNothing().when(blogRepository).deleteById(1L);

        blogService.delete(1L);
        verify(blogRepository, times(1)).deleteById(1L);
    }
}
