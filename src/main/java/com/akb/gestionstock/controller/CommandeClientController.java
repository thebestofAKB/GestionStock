package com.akb.gestionstock.controller;

import com.akb.gestionstock.controller.api.CommandeClientApi;
import com.akb.gestionstock.dto.CommandeClientDto;
import com.akb.gestionstock.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommandeClientController implements CommandeClientApi {

    private CommandeClientService comCliService;

    @Autowired
    public CommandeClientController(CommandeClientService comCliService) {
        this.comCliService = comCliService;
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        return comCliService.findById(id);
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        return comCliService.findByCode(code);
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeCltDto) {
        return comCliService.save(commandeCltDto);
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return comCliService.findAll();
    }

    @Override
    public void delete(Integer id) {
        comCliService.delete(id);
    }
}
