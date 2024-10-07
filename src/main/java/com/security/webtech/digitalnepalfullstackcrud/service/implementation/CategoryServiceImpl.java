package com.security.webtech.digitalnepalfullstackcrud.service.implementation;

import com.security.webtech.digitalnepalfullstackcrud.entity.Category;
import com.security.webtech.digitalnepalfullstackcrud.repository.CategoryRepository;
import com.security.webtech.digitalnepalfullstackcrud.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, Long id) {
        boolean isExist=categoryRepository.existsById(id);
        if (isExist) {
            Category isExistingCategory=categoryRepository.findById(id).get();
            isExistingCategory.setName(category.getName());
            return categoryRepository.save(isExistingCategory);
        }else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
       categoryRepository.deleteById(id);
    }
}
