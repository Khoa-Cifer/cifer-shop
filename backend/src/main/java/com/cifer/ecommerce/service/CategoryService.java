package com.cifer.ecommerce.service;

import com.cifer.ecommerce.model.Category;
import com.cifer.ecommerce.model.User;
import com.cifer.ecommerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    @Autowired
    private final CategoryRepository repository;

    @Override
    public Category createCategory(String name) {
        Category category = new Category();
        Optional<List<String>> allExistedName = repository.getAllName();
        for (int i = 0; i < allExistedName.get().size(); i++) {
            if (name.equalsIgnoreCase(allExistedName.get().get(i))) {
                return null;
            }
        }
        category.setName(name);
        return repository.save(category);
    }

    @Override
    public String updateCategory(Long id, String name) {
        Optional<Category> oldCategory = repository.findById(id);
        if (oldCategory.isPresent()) {
            Category newCategory = oldCategory.get();

            if (name != null) {
                newCategory.setName(name);
            }

            repository.save(newCategory);
            return "Update successfully";
        }
        return "Update unsuccessfully, maybe wrong id";
    }

    @Override
    public String deleteCategory(Long id) {
        Optional<Category> deletedCategory = repository.findById(id);
        if (deletedCategory.isPresent()) {
            repository.deleteById(id);
            return "Delete successfully";
        }
        return "Nothing happened, maybe category does not exist";
    }

    @Override
    public List<Category> getAllCategories() {
        return repository.findAll();
    }
}
