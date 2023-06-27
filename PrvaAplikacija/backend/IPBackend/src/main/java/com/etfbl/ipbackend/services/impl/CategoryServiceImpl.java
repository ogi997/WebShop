package com.etfbl.ipbackend.services.impl;

import com.etfbl.ipbackend.exceptions.ExistingException;
import com.etfbl.ipbackend.models.Category;
import com.etfbl.ipbackend.models.requests.CategoryRequest;
import com.etfbl.ipbackend.repositories.CategoryRepository;
import com.etfbl.ipbackend.services.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryRequest categoryRequest) {
        Category category = new Category();
        category.setName(categoryRequest.getName());
//        category.setFkAttribute(categoryRequest.getFkAttribute());
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> getCategoryById(Integer id) {
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> getAllCategories(Byte deleted) {
        return categoryRepository.findAllByDeleted(deleted);
    }

    @Override
    public Category updateCategory(Integer id, CategoryRequest categoryRequest) throws ExistingException {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty())
            throw new ExistingException("Kategorija sa ID: " + id + " ne postoji u bazi podataka");

        Category existingCategory = categoryOptional.get();
        existingCategory.setName(categoryRequest.getName());
//        existingCategory.setFkAttribute(categoryRequest.getFkAttribute());

        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Integer id) {
        categoryRepository.deleteById(id);
    }
}
