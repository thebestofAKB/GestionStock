package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.Utilisateur;
import jdk.jshell.execution.Util;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class UtilisateurDto {

    private Integer id;

    private String nom;

    private String prenom;

    private String email;

    private Instant dateNaissance;

    private String motDePasse;

    private AdresseDto adresse;

    private String photo;

    private EntrepriseDto entrepriseDto;

    private List<RoleDto> roles;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur) {

        if (utilisateur == null) {

            //TODO Throw an Exception
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .prenom(utilisateur.getPrenom())
                .email(utilisateur.getEmail())
                .dateNaissance(utilisateur.getDateNaissance())
                .motDePasse(utilisateur.getMotDePasse())
                .adresse(AdresseDto.fromEntity(utilisateur.getAdresse()))
                .photo(utilisateur.getPhoto())
                .entrepriseDto(EntrepriseDto.fromEntity(utilisateur.getEntreprise()))
                .roles(
                        utilisateur.getRoles() != null ?
                                utilisateur.getRoles().stream()
                                .map(RoleDto::fromEntity)
                                .collect(Collectors.toList()): null

                )
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto utilisateurDto) {

        if (utilisateurDto == null) {

            //TODO Throw an Exception
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setNom(utilisateurDto.getNom());
        utilisateur.setPrenom(utilisateurDto.getPrenom());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setDateNaissance(utilisateurDto.getDateNaissance());
        utilisateur.setAdresse(AdresseDto.toEntity(utilisateurDto.getAdresse()));
        utilisateur.setMotDePasse(utilisateurDto.getMotDePasse());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setEntreprise(EntrepriseDto
                .toEntity(utilisateurDto.getEntrepriseDto()));

        utilisateur.setRoles(
                utilisateurDto.getRoles() != null ?
                        utilisateurDto.getRoles().stream()
                                .map(RoleDto::toEntity)
                                .collect(Collectors.toList()): null
        );

        return utilisateur;
    }
}
