package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto findById(Integer id);
    ClientDto save(ClientDto clientDto);
    List<ClientDto> findAll();
    void delete(Integer id);
}
