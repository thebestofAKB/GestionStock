package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.MouvementStockDto;

import java.util.List;

public interface MouvementStockService {

    MouvementStockDto findById(Integer id);
    MouvementStockDto save(MouvementStockDto mouvementStockDto);
    List<MouvementStockDto> findAll();
    void delete(Integer id);
}
