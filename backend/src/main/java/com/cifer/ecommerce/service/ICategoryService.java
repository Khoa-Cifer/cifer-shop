package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(String name);
    String updateCategory(String oldName, String newName);
    String deleteCategory(String name);
    List<Category> getAllCategories();
}
