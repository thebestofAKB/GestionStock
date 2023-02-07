package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.VenteDto;

import java.util.List;

public interface VenteService {

    VenteDto findById(Integer id);
    VenteDto findByCode(String code);
    List<VenteDto> findAll();
    VenteDto save(VenteDto venteDto);
    void delete(Integer id);
}
