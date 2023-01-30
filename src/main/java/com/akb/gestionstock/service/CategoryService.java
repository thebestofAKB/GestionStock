package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto findById(Integer id);
    CategoryDto findByCode(String codeCategory);
    CategoryDto findByDesignation(String designation);
    List<CategoryDto> findAll();
    CategoryDto save(CategoryDto categoryDto);
    void delete(Integer id);
}
