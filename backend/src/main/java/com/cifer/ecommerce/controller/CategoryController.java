package com.cifer.ecommerce.controller;

import com.cifer.ecommerce.model.Category;
import com.cifer.ecommerce.service.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {
    private final ICategoryService categoryService;

    @PostMapping("/create/new-category")
    public ResponseEntity<?> createCategory(@RequestParam("name") String name) {
        Category newCategory = categoryService.createCategory(name);
        return ResponseEntity.ok(newCategory);
    }

    @PutMapping("/update/category/{oldName}")
    public ResponseEntity<String> updateCategory(@PathVariable("oldName") String oldName, @RequestParam("newName") String newName) {
        String categoryResponse = categoryService.updateCategory(oldName, newName);
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/delete/category/{name}")
    public ResponseEntity<String> deleteCategory(@PathVariable("name") String name) {
        String deletedCategory = categoryService.deleteCategory(name);
        return ResponseEntity.ok(deletedCategory);
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> allCategories = categoryService.getAllCategories();
        return ResponseEntity.ok(allCategories);
    }
}
