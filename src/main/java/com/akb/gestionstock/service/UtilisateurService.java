package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto findById(Integer id);
    UtilisateurDto findByEmail(String email);
    UtilisateurDto save(UtilisateurDto utilisateurDto);
    List<UtilisateurDto> findAll();
    void delete(Integer id);
}
