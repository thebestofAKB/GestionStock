package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.ClientDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Client;
import com.akb.gestionstock.repository.ClientRepository;
import com.akb.gestionstock.service.ClientService;
import com.akb.gestionstock.validator.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto findById(Integer id) {

        if (id == null) {
            log.error("Client ID is null {}", id);
            return null;
        }

        return clientRepository.findById(id)
                .map(ClientDto::fromEntity)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("L'ID %d du client n'a pas ete trouve", id),
                                ErrorCodes.CLIENT_NOT_FOUND
                        )
                );
    }

    @Override
    public ClientDto save(ClientDto clientDto) {

        List<String> errors = ClientValidator.validate(clientDto);

        if (!errors.isEmpty()) {
            log.error("Client is not valid {}", clientDto);
            throw new InvalidEntityException("Le Client n'est pas valide.", ErrorCodes.CLIENT_NOT_VALID, errors);
        }

        Client savedClient = clientRepository.save(ClientDto.toEntity(clientDto));

        return ClientDto.fromEntity(savedClient);
    }

    @Override
    public List<ClientDto> findAll() {

        return clientRepository.findAll().stream()
                .map(ClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("ID is null");
        }

        clientRepository.deleteById(id);
    }
}
