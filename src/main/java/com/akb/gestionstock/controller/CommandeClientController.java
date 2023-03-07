package com.akb.gestionstock.controller;

import com.akb.gestionstock.controller.api.CommandeClientApi;
import com.akb.gestionstock.dto.CommandeClientDto;
import com.akb.gestionstock.service.CommandeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CommandeClientDto> findById(Integer id) {
        CommandeClientDto commandeClientDto = comCliService.findById(id);
        return new ResponseEntity<>(commandeClientDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommandeClientDto> findByCode(String code) {
        CommandeClientDto commandeClientDto = comCliService.findByCode(code);
        return new ResponseEntity<>(commandeClientDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<CommandeClientDto> save(CommandeClientDto commandeCltDto) {
        CommandeClientDto commandeClientDto = comCliService.save(commandeCltDto);
        return new ResponseEntity<>(commandeClientDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<CommandeClientDto>> findAll() {
        List<CommandeClientDto> commandeClients = comCliService.findAll();
        return new ResponseEntity<>(commandeClients, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(Integer id) {
        comCliService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
