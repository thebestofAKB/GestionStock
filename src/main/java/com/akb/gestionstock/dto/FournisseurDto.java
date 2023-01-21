package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.Adresse;
import com.akb.gestionstock.model.Fournisseur;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FournisseurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private AdresseDto adresseDto;

    private String photo;

    private String email;

    private String telephone;

    @JsonIgnore
    private List<CommandeFournisseurDto> commandeFournisseurs;

    public static FournisseurDto fromEntity(Fournisseur fournisseur) {

        if (fournisseur == null) {

            //TODO Throw an Exception
            return null;
        }

        return FournisseurDto.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .prenom(fournisseur.getPrenom())
                .adresseDto(AdresseDto.fromEntity(fournisseur.getAdresse()))
                .email(fournisseur.getEmail())
                .telephone(fournisseur.getTelephone())
                .photo(fournisseur.getPhoto())
                .build();
    }

    public static Fournisseur toEntity(FournisseurDto fournisseurDto) {

        if (fournisseurDto == null) {

            //TODO Throw an Exception
            return null;
        }

        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(fournisseurDto.getId());
        fournisseur.setNom(fournisseurDto.getNom());
        fournisseur.setPrenom(fournisseurDto.getPrenom());
        fournisseur.setAdresse(AdresseDto.toEntity(fournisseurDto.getAdresseDto()));
        fournisseur.setEmail(fournisseurDto.getEmail());
        fournisseur.setTelephone(fournisseurDto.getTelephone());
        fournisseur.setPhoto(fournisseurDto.getPhoto());

        return fournisseur;
    }
}
