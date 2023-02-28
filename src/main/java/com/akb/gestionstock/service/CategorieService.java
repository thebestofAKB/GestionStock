package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.CategorieDto;

import java.util.List;

public interface CategorieService {
    CategorieDto findById(Integer id);
    CategorieDto findByCode(String codeCategory);
    CategorieDto findByDesignation(String designation);
    List<CategorieDto> findAll();
    CategorieDto save(CategorieDto categorieDto);
    void delete(Integer id);
}
