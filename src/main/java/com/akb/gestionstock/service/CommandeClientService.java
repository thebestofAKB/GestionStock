package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.CommandeClientDto;

import java.util.List;

public interface CommandeClientService {

    CommandeClientDto findById(Integer id);
    CommandeClientDto findByCode(String code);
    CommandeClientDto save(CommandeClientDto commandeCltDto);
    List<CommandeClientDto> findAll();
    void delete(Integer id);
}
