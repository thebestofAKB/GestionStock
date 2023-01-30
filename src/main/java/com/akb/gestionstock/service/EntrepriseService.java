package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {

    EntrepriseDto findById(Integer id);
    EntrepriseDto save(EntrepriseDto entrepriseDto);
    List<EntrepriseDto> findAll();
    void delete(Integer id);
}
