package com.ecommerce.project.service;

import com.ecommerce.project.entity.Category;
import com.ecommerce.project.payload.CategoryDTO;
import com.ecommerce.project.payload.CategoryResponse;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long id);
    CategoryResponse getAllCategories();
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(Long categoryId);
    CategoryDTO updateCategory(Long id, CategoryDTO categoryDTO);
}
