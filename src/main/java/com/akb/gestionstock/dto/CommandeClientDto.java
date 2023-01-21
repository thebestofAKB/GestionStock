package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.CommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class CommandeClientDto {

    private Integer id;

    private String code;

    private Instant dateCommande;

    private ClientDto clientDto;

    @JsonIgnore
    private List<LigneCommandeClientDto> ligneCommandeClients;

    public static CommandeClientDto fromEntity(CommandeClient commandeClient) {

        if (commandeClient == null) {

            //TODO Throw an Exception
            return null;
        }

        return CommandeClientDto.builder()
                .id(commandeClient.getId())
                .code(commandeClient.getCode())
                .dateCommande(commandeClient.getDateCommande())
                .clientDto(ClientDto.fromEntity(commandeClient.getClient()))
                .build();

    }

    public static CommandeClient toEntity(CommandeClientDto commandeClientDto) {

        if (commandeClientDto == null) {

            //TODO Throw an Exception
            return null;
        }

        CommandeClient commandeClient = new CommandeClient();
        commandeClient.setId(commandeClientDto.getId());
        commandeClient.setCode(commandeClientDto.getCode());
        commandeClient.setDateCommande(commandeClientDto.getDateCommande());
        commandeClient.setClient(ClientDto.toEntity(commandeClientDto.getClientDto()));

        return commandeClient;
    }
}
