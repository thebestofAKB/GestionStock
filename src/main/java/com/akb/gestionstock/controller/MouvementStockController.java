package com.akb.gestionstock.controller;

import com.akb.gestionstock.controller.api.MouvementStockApi;
import com.akb.gestionstock.dto.MouvementStockDto;
import com.akb.gestionstock.service.MouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MouvementStockController implements MouvementStockApi {

    private MouvementStockService mvtStockService;

    @Autowired
    public MouvementStockController(MouvementStockService mvtStockService) {
        this.mvtStockService = mvtStockService;
    }

    @Override
    public MouvementStockDto findById(Integer id) {
        return mvtStockService.findById(id);
    }

    @Override
    public MouvementStockDto save(MouvementStockDto mouvementStockDto) {
        return mvtStockService.save(mouvementStockDto);
    }

    @Override
    public List<MouvementStockDto> findAll() {
        return mvtStockService.findAll();
    }

    @Override
    public void delete(Integer id) {
        mvtStockService.delete(id);
    }
}
