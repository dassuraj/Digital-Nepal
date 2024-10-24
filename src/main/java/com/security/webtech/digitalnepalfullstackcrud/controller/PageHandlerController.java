package com.security.webtech.digitalnepalfullstackcrud.controller;

import com.security.webtech.digitalnepalfullstackcrud.entity.Blog;
import com.security.webtech.digitalnepalfullstackcrud.entity.Category;
import com.security.webtech.digitalnepalfullstackcrud.entity.Tag;
import com.security.webtech.digitalnepalfullstackcrud.service.BlogService;
import com.security.webtech.digitalnepalfullstackcrud.service.CategoryService;
import com.security.webtech.digitalnepalfullstackcrud.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/page")
public class PageHandlerController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TagService tagService;
    @Autowired
    private BlogService blogService;


    // Handler method for creating blogs
    // localhost:8080/page/create-blog
    @GetMapping("/create-blog")
    public String createBlog(Model model) {
        model.addAttribute("blog", new Blog());  // Ensure the attribute name matches
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("tags", tagService.findAll());
        return "create-blog";
    }

    // Handler method for POST operation
    // localhost:8080/page/save-blog
    @PostMapping("/save-blog")
    public String saveBlog(@ModelAttribute Blog blog,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            model.addAttribute("tags", tagService.findAll());
            return "create-blog"; // Return to the form with validation errors
        }

        Blog savedBlog = blogService.save(blog);
        model.addAttribute("savedBlog", savedBlog);
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("tags", tagService.findAll());

        return "save-blog"; // Show the saved blog details
    }

    //handler method for create category
    //localhost:8080/page/create-category
    @GetMapping("/create-category")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "create-category";
    }

    //handler method for saving category
    //localhost:8080/page/save-category
    @PostMapping("/save-category")
    public String saveCategory(@ModelAttribute Category category, Model model) {
        categoryService.save(category);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categoryData", categories);
        return "save-category";
    }

    //handler method for create tag
    //localhost:8080/page/create-category
    @GetMapping("/create-tag")
    public String createTag(Model model) {
        model.addAttribute("tags", new Tag());
        return "create-tag";
    }

    //handler method for save Tag
    //localhost:8080/page/save-tag
    @PostMapping("/save-tag")
    public String saveTag(@ModelAttribute Tag tag, Model model) {
        tagService.save(tag);
        model.addAttribute("tags", tagService.findAll());
        return "save-tag";
    }

}
