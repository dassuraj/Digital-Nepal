package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.Blog;
import com.security.webtech.digitalnepalfullstackcrud.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("blog")
public class BlogController {
    @Autowired
    private BlogService blogService;


    // Create a new blog
    //localhost:8080/blog/blog/save
    @PostMapping("/save")
    public ResponseEntity<Blog> createBlog(@RequestBody Blog blog) {
        Blog createdBlog = blogService.save(blog);
        return new ResponseEntity<>(createdBlog, HttpStatus.CREATED);
    }

    // Get all blogs
    //localhost:8080/blog/find-all
    @GetMapping("/find-all")
    public ResponseEntity<List<Blog>> getAllBlogs() {
        List<Blog> blogs = blogService.findAll();
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

    // Get a blog by ID
    //localhost:8080/blog/find/id
    @GetMapping("/find/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable Long id) {
        Blog blog = blogService.findById(id);
        if (blog != null) {
            return new ResponseEntity<>(blog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Update a blog
    //localhost:8080/blog/update/id
    @PutMapping("/update/{id}")
    public ResponseEntity<Blog> updateBlog(@PathVariable Long id, @RequestBody Blog blog) {
        Blog existingBlog = blogService.findById(id);
        if (existingBlog != null) {
            blog.setId(id); // ensure the ID is set for the update
            Blog updatedBlog = blogService.save(blog);
            return new ResponseEntity<>(updatedBlog, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete a blog
    //localhost:8080/blog/delete/id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBlog(@PathVariable Long id) {
        if (blogService.findById(id) != null) {
           blogService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}




