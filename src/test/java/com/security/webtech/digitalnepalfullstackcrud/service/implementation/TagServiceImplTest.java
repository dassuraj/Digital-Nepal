package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Tag;
import com.security.webtech.digitalnepalfullstackcrud.repository.TagRepository;
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

class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    private Tag tag;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tag = new Tag();
        tag.setId(1L);
        tag.setName("Tech");
    }

    @Test
    void findAll() {
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        when(tagRepository.findAll()).thenReturn(tagList);

        List<Tag> result = tagService.findAll();
        assertEquals(1, result.size());
        verify(tagRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));

        Tag result = tagService.findById(1L);
        assertNotNull(result);
        assertEquals("Tech", result.getName());
        verify(tagRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        when(tagRepository.save(any(Tag.class))).thenReturn(tag);

        Tag result = tagService.save(tag);
        assertNotNull(result);
        assertEquals("Tech", result.getName());
        verify(tagRepository, times(1)).save(tag);
    }

    @Test
    void update() {
        when(tagRepository.existsById(1L)).thenReturn(true);
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));
        when(tagRepository.save(tag)).thenReturn(tag);

        Tag updatedTag = new Tag();
        updatedTag.setName("Updated Tech");
        Tag result = tagService.update(updatedTag, 1L);
        assertEquals("Updated Tech", result.getName());
        verify(tagRepository, times(1)).save(tag);
    }

    @Test
    void delete() {
        doNothing().when(tagRepository).deleteById(1L);

        tagService.delete(1L);
        verify(tagRepository, times(1)).deleteById(1L);
    }
}
