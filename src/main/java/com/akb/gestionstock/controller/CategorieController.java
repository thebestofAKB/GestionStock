package com.akb.gestionstock.controller;

import com.akb.gestionstock.controller.api.CategorieApi;
import com.akb.gestionstock.dto.CategorieDto;
import com.akb.gestionstock.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategorieController implements CategorieApi {

    private CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @Override
    public CategorieDto save(CategorieDto categorieDto) {
        return categorieService.save(categorieDto);
    }

    @Override
    public CategorieDto findById(Integer id) {
        return categorieService.findById(id);
    }

    @Override
    public CategorieDto findByCode(String codeCategory) {
        return categorieService.findByCode(codeCategory);
    }

    @Override
    public CategorieDto findByDesignation(String designation) {
        return categorieService.findByDesignation(designation);
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieService.findAll();
    }

    @Override
    public void delete(Integer id) {
        categorieService.delete(id);
    }
}
