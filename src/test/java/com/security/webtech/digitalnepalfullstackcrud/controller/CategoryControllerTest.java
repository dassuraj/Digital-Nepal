package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.Category;
import com.security.webtech.digitalnepalfullstackcrud.service.CategoryService;
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

class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setId(1L);
        category.setName("Sample Category");
    }

    @Test
    void createCategory() {
        when(categoryService.save(any(Category.class))).thenReturn(category);

        ResponseEntity<Category> response = categoryController.createCategory(category);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Category", response.getBody().getName());
        verify(categoryService, times(1)).save(any(Category.class));
    }

    @Test
    void getAllCategories() {
        when(categoryService.findAll()).thenReturn(Arrays.asList(category));

        ResponseEntity<List<Category>> response = categoryController.getAllCategories();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(categoryService, times(1)).findAll();
    }

    @Test
    void getCategoryById() {
        when(categoryService.findById(anyLong())).thenReturn(category);

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Category", response.getBody().getName());
        verify(categoryService, times(1)).findById(anyLong());
    }

    @Test
    void getCategoryById_NotFound() {
        when(categoryService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Category> response = categoryController.getCategoryById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).findById(anyLong());
    }

    @Test
    void updateCategory() {
        when(categoryService.findById(anyLong())).thenReturn(category);
        when(categoryService.save(any(Category.class))).thenReturn(category);

        ResponseEntity<Category> response = categoryController.updateCategory(1L, category);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Sample Category", response.getBody().getName());
        verify(categoryService, times(1)).findById(anyLong());
        verify(categoryService, times(1)).save(any(Category.class));
    }

    @Test
    void updateCategory_NotFound() {
        when(categoryService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Category> response = categoryController.updateCategory(1L, category);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).findById(anyLong());
    }

    @Test
    void deleteCategory() {
        when(categoryService.findById(anyLong())).thenReturn(category);
        doNothing().when(categoryService).delete(anyLong());

        ResponseEntity<Void> response = categoryController.deleteCategory(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(categoryService, times(1)).findById(anyLong());
        verify(categoryService, times(1)).delete(anyLong());
    }

    @Test
    void deleteCategory_NotFound() {
        when(categoryService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Void> response = categoryController.deleteCategory(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(categoryService, times(1)).findById(anyLong());
    }
}
