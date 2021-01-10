package com.ewd.report.controller;

import com.ewd.report.entity.Category;
import com.ewd.report.service.Interfaces.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category Category){
        return categoryService.addCategory(Category);
    }

    @GetMapping("/category/{id}")
    public Category getCategoryById(@PathVariable(value = "id") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/category/{id}")
    public boolean updateCategory(@Valid @RequestBody Category category, @PathVariable("id") Long id){
        return categoryService.updateCategory(category, id);
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
    }
}
