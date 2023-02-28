package com.akb.gestionstock.controller;

import com.akb.gestionstock.controller.api.CommandeFournisseurApi;
import com.akb.gestionstock.dto.CommandeFournisseurDto;
import com.akb.gestionstock.service.CommandeFournisseurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeFournisseurController implements CommandeFournisseurApi {

    private CommandeFournisseurService comFourService;

    @Autowired
    public CommandeFournisseurController(CommandeFournisseurService comFourService){
        this.comFourService = comFourService;
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {
        return comFourService.findById(id);
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFourDto) {
        return comFourService.save(commandeFourDto);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {
        return comFourService.findAll();
    }

    @Override
    public void delete(Integer id) {
        comFourService.delete(id);
    }
}
