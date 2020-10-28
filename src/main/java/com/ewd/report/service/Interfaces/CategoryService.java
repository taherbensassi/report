package com.ewd.report.service.Interfaces;


import com.ewd.report.entity.Category;

import java.util.List;

public interface CategoryService  {

    List<Category> getAllCategories();

    Category addCategory(Category category);

    Category getCategoryById(Long id);

    Boolean updateCategory(Category category, Long id);

    void deleteCategory(Long id);
}
