package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.Tag;
import com.security.webtech.digitalnepalfullstackcrud.service.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class TagControllerTest {

    @Mock
    private TagService tagService;

    @InjectMocks
    private TagController tagController;

    private Tag tag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tag = new Tag();
        tag.setId(1L);
        tag.setName("Sample Tag");
    }

    @Test
    void createTag() {
        when(tagService.save(any(Tag.class))).thenReturn(tag);

        ResponseEntity<Tag> response = tagController.createTag(tag);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Tag", response.getBody().getName());
        verify(tagService, times(1)).save(any(Tag.class));
    }

    @Test
    void getAllTags() {
        when(tagService.findAll()).thenReturn(Arrays.asList(tag));

        ResponseEntity<List<Tag>> response = tagController.getAllTags();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        verify(tagService, times(1)).findAll();
    }

    @Test
    void getTagById() {
        when(tagService.findById(anyLong())).thenReturn(tag);

        ResponseEntity<Tag> response = tagController.getTagById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Tag", response.getBody().getName());
        verify(tagService, times(1)).findById(anyLong());
    }

    @Test
    void updateTag() {
        when(tagService.findById(anyLong())).thenReturn(tag);
        when(tagService.save(any(Tag.class))).thenReturn(tag);

        ResponseEntity<Tag> response = tagController.updateTag(1L, tag);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Sample Tag", response.getBody().getName());
        verify(tagService, times(1)).findById(anyLong());
        verify(tagService, times(1)).save(any(Tag.class));
    }

    @Test
    void deleteTag() {
        when(tagService.findById(anyLong())).thenReturn(tag);

        ResponseEntity<Void> response = tagController.deleteTag(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(tagService, times(1)).delete(anyLong());
    }

    @Test
    void getTagById_NotFound() {
        when(tagService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Tag> response = tagController.getTagById(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(tagService, times(1)).findById(anyLong());
    }

    @Test
    void updateTag_NotFound() {
        when(tagService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Tag> response = tagController.updateTag(1L, tag);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(tagService, times(1)).findById(anyLong());
    }

    @Test
    void deleteTag_NotFound() {
        when(tagService.findById(anyLong())).thenReturn(null);

        ResponseEntity<Void> response = tagController.deleteTag(1L);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(tagService, times(1)).findById(anyLong());
    }
}
