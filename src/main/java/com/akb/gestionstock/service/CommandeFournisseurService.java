package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.CommandeFournisseurDto;

import java.util.List;

public interface CommandeFournisseurService {

    CommandeFournisseurDto findById(Integer id);
    CommandeFournisseurDto save(CommandeFournisseurDto commandeFourDto);
    List<CommandeFournisseurDto> findAll();
    void delete(Integer id);
}
