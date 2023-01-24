package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.RoleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RoleValidator {

    public static List<String> validate(RoleDto roleDto) {
        List<String> errors = new ArrayList<>();

        if (roleDto == null) {
            errors.add("Veuillez renseigner le nom du role");
            errors.add("Veuillez selectionnez l'utilisateur de ce role");

            return errors;
        }

        if (!StringUtils.hasLength(roleDto.getNom())) {
            errors.add("Veuillez renseigner le nom du role");
        }

        if (roleDto.getUtilisateurDto() == null) {
            errors.add("Veuillez selectionnez l'utilisateur de ce role");
        }

        return errors;
    }
}
