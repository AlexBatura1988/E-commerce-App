package com.ecommerce.project.service;

import com.ecommerce.project.entity.Category;
import com.ecommerce.project.exception.APIExceptions;
import com.ecommerce.project.exception.ResourceNotFoundException;
import com.ecommerce.project.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if(categories.isEmpty()) {
            throw new APIExceptions("no categories created till now");
        }
        return categories;
    }

    @Override
    public Category createCategory(Category category) {
        Category savedCategory = categoryRepository.findByCategoryName(category.getCategoryName());
        if (savedCategory != null) {
            throw new APIExceptions("Category already exists with name: " + category.getCategoryName());
        }

        return categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        categoryRepository.delete(category);
        return "Category successfully deleted";
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Category category1 = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", id));

        if (Objects.equals(category1.getCategoryName(), category.getCategoryName())) {
            throw new APIExceptions("Category already exists with name: " + category.getCategoryName());
        }
        category1.setCategoryName(category.getCategoryName());
        return categoryRepository.save(category1);
    }
}
