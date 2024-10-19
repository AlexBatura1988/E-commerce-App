package com.ecommerce.project.service;

import com.ecommerce.project.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    List<Category> getAllCategories();
    Category createCategory(Category category);
    String deleteCategory(Long categoryId);
    Category updateCategory(Long id, Category category);
}
