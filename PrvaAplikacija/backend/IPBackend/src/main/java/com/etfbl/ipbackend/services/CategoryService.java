package com.etfbl.ipbackend.services;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Category;
import com.etfbl.ipbackend.models.requests.CategoryRequest;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Category createCategory(CategoryRequest categoryRequest);

    Optional<Category> getCategoryById(Integer id);

    List<Category> getAllCategories(Byte deleted);

    Category updateCategory(Integer id, CategoryRequest categoryRequest) throws ExistingException;

    void deleteCategory(Integer id);
}
