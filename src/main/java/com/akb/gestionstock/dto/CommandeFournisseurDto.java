package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.CommandeFournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommandeFournisseurDto {

    private Integer id;

    private FournisseurDto fournisseurDto;

    private Integer idEntreprise;

    @JsonIgnore
    private List<LigneCommandeFournisseurDto> ligneCommandeFournisseurs;

    public static CommandeFournisseurDto fromEntity(CommandeFournisseur commandeFournisseur) {

        if (commandeFournisseur == null) {

            //TODO Throw an Exception
            return null;
        }

        return CommandeFournisseurDto.builder()
                .id(commandeFournisseur.getId())
                .fournisseurDto(FournisseurDto.
                        fromEntity(commandeFournisseur.getFournisseur()))
                .idEntreprise(commandeFournisseur.getIdEntreprise())
                .build();
    }

    public static CommandeFournisseur toEntity(
            CommandeFournisseurDto commandeFournisseurDto) {

        if (commandeFournisseurDto == null) {

            //TODO Throw an Exception
            return null;
        }

        CommandeFournisseur commandeFournisseur = new CommandeFournisseur();
        commandeFournisseur.setId(commandeFournisseurDto.getId());
        commandeFournisseur.setFournisseur(
                FournisseurDto.toEntity(commandeFournisseurDto.getFournisseurDto()));
        commandeFournisseur.setIdEntreprise(commandeFournisseurDto.getIdEntreprise());

        return commandeFournisseur;
    }

}
