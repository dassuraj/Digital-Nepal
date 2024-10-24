package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Category;
import com.security.webtech.digitalnepalfullstackcrud.repository.CategoryRepository;
import com.security.webtech.digitalnepalfullstackcrud.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService = new CategoryServiceImpl();

    private Category category;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        category = new Category();
        category.setId(1L);
        category.setName("Sample Category");
    }

    @Test
    void findAll() {
        when(categoryRepository.findAll()).thenReturn(Arrays.asList(category));

        assertEquals(1, categoryService.findAll().size());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        Category foundCategory = categoryService.findById(1L);
        assertNotNull(foundCategory);
        assertEquals("Sample Category", foundCategory.getName());
        verify(categoryRepository, times(1)).findById(anyLong());
    }

    @Test
    void save() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category savedCategory = categoryService.save(category);
        assertNotNull(savedCategory);
        assertEquals("Sample Category", savedCategory.getName());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void update() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        category.setName("Updated Category Name");
        Category updatedCategory = categoryService.update( category,1L);
        assertEquals("Updated Category Name", updatedCategory.getName());
        verify(categoryRepository, times(1)).findById(anyLong());
        verify(categoryRepository, times(1)).save(any(Category.class));
    }

    @Test
    void delete() {
        doNothing().when(categoryRepository).deleteById(anyLong());

        categoryService.delete(1L);
        verify(categoryRepository, times(1)).deleteById(anyLong());
    }
}
