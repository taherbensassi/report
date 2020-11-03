package com.ewd.report.controller.moderator;

import com.ewd.report.entity.Category;
import com.ewd.report.service.Interfaces.CategoryService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/admin")
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
