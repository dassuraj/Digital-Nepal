package com.security.webtech.digitalnepalfullstackcrud.service;

import com.security.webtech.digitalnepalfullstackcrud.entity.Blog;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BlogService {
    public List<Blog> findAll();
    public Blog findById(Long id);
    public Blog save(Blog blog);
    public Blog update(Blog blog ,Long id);
    public void delete(Long id);

}
