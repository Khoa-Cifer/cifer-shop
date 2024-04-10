package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(String name);
    String updateCategory(Long id, String name);
    String deleteCategory(Long id);
    List<Category> getAllCategories();
}
