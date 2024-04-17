package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Category;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    @Autowired
    private final CategoryRepository repository;

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        boolean categoryExistenceCheck = categoriesNameDuplicatedCheck(name);
        if (categoryExistenceCheck) {
            category.setName(name);
            return repository.save(category);
        }
        return null;
    }

    @Override
    public String updateCategory(String oldName, String newName) {
        Optional<Category> oldCategory = repository.findByName(oldName);
        if (oldCategory.isPresent()) {
            Category newCategory = oldCategory.get();
            boolean categoryExistenceCheck = categoriesNameDuplicatedCheck(newName);
            if (categoryExistenceCheck) {
                if (!Objects.equals(newName, "")) {
                    newCategory.setName(newName);
                    repository.save(newCategory);
                    return "Update successfully";
                }
            }
        }
        return "Update unsuccessfully, maybe wrong id";
    }

    @Override
    public String deleteCategory(String name) {
        Optional<Category> deletedCategory = repository.findByName(name);
        if (deletedCategory.isPresent()) {
            repository.deleteById(deletedCategory.get().getId());
            return "Delete successfully";
        }
        return "Nothing happened, maybe category does not exist";
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Optional<List<String>> getAllCategoriesName() {
        return repository.getAllName();
    }

    public boolean categoriesNameDuplicatedCheck(String name) {
        for (int i = 0; i < getAllCategoriesName().get().size(); i++) {
            if (name.equalsIgnoreCase(getAllCategoriesName().get().get(i))) {
                return false;
            }
        }
        return true;
    }
}
