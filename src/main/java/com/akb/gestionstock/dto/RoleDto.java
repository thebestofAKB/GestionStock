package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.Role;
import com.akb.gestionstock.model.Utilisateur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDto {

    private Integer id;

    private String nom;

    private UtilisateurDto utilisateurDto;

    public static RoleDto fromEntity(Role role) {

        if (role == null) {

            //TODO Throw an Exception
            return null;
        }

        return RoleDto.builder()
                .id(role.getId())
                .nom(role.getNom())
                .utilisateurDto(UtilisateurDto.fromEntity(role.getUtilisateur()))
                .build();
    }

    public static Role toEntity(RoleDto roleDto) {

        if (roleDto == null) {

            //TODO Throw an Exception
            return null;
        }

        Role role = new Role();
        role.setId(roleDto.getId());
        role.setNom(roleDto.getNom());
        role.setUtilisateur(UtilisateurDto.toEntity(roleDto.getUtilisateurDto()));

        return role;
    }
}
