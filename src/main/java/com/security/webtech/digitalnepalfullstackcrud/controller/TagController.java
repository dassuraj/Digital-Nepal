package com.security.webtech.digitalnepalfullstackcrud.controller;
import com.security.webtech.digitalnepalfullstackcrud.entity.Tag;
import com.security.webtech.digitalnepalfullstackcrud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tags")
public class TagController {

    @Autowired
    private TagService tagService;

    // Create a new tag
    //localhost:8080/api/tags/save
    @PostMapping("/save")
    public ResponseEntity<Tag> createTag(@RequestBody Tag tag) {
        Tag createdTag = tagService.save(tag);
        return new ResponseEntity<Tag>(createdTag, HttpStatus.CREATED);
    }

    // Get all tags
    //localhost:8080/api/tags/find-all
    @GetMapping("/find-all")
    public ResponseEntity<List<Tag>> getAllTags() {
        List<Tag> tags = tagService.findAll();
        return new ResponseEntity<>(tags, HttpStatus.OK);
    }

    // Get a tag by ID
    //localhost:8080/api/tags/find/id
    @GetMapping("/find/{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable Long id) {
        Tag tag = tagService.findById(id);
        if (tag != null) {
            return new ResponseEntity<>(tag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a tag
    //localhost:8080/api/tags/update/id
    @PutMapping("/update/{id}")
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        Tag existingTag = tagService.findById(id);
        if (existingTag != null) {
            tag.setId(id); // Ensure the ID is set for the update
            Tag updatedTag = tagService.save(tag);
            return new ResponseEntity<>(updatedTag, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a tag
    //localhost:8080/api/tags/delete/id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTag(@PathVariable Long id) {
        if (tagService.findById(id) != null) {
            tagService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
