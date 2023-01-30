package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.FournisseurDto;

import java.util.List;

public interface FournisseurService {

    FournisseurDto findById(Integer id);
    FournisseurDto save(FournisseurDto fournisseurDto);
    List<FournisseurDto> findAll();
    void delete(Integer id);
}
