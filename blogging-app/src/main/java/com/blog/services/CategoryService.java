package com.blog.services;

import com.blog.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);
    List<CategoryDto> getAllCategory();
    CategoryDto getCategoryById(Integer categoryId);
    void deleteCategory(Integer categoryId);
}
