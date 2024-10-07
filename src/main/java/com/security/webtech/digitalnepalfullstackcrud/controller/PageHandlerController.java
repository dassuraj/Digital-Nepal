package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.Blog;
import com.security.webtech.digitalnepalfullstackcrud.entity.Category;
import com.security.webtech.digitalnepalfullstackcrud.service.BlogService;
import com.security.webtech.digitalnepalfullstackcrud.service.CategoryService;
import com.security.webtech.digitalnepalfullstackcrud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class PageHandlerController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;


    //handler method for creating blogs
    //localhost:8080/api/create-blog
    @GetMapping("/create-blog")
    public String createBlog(Model model){
        model.addAttribute("blogs",new Blog());
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("tags",tagService.findAll());
        return "create-blog";
    }

    //handler method for post operation
    @PostMapping("/save-blog")
    public String saveBlog(@ModelAttribute Blog blog,Model model){
       // model.addAttribute("blogData",blogService.save(blog));
        return "/save-blog";
    }

    //handler method for create category
    //localhost:8080/api/create-category
    @GetMapping("/create-category")
    public String createCategory(Model model){
        model.addAttribute("category",new Category());
        return "create-category";
    }

    //handler method for saving category
    //localhost:8080/api/save-category
    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute Category category,Model model){
        model.addAttribute("categoryData",categoryService.save(category));
        return "save-category";
    }
}
