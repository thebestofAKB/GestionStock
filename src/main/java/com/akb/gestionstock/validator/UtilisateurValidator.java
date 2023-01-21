package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {

        List<String> errors = new ArrayList<>();

        if (utilisateurDto == null) {
            errors.add("Veuillez renseigner le 'nom' de l'utilisateur.");
            errors.add("Veuillez renseigner le 'prenom' de l'utilisateur.");
            errors.add("Veuillez renseigner le 'mot de passe' de l'utilisateur.");
        }

        if (!StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le 'nom' de l'utilisateur.");
        }

        if (!StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le 'prenom' de l'utilisateur.");
        }

        if (!StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner le 'E-mail' de l'utilisateur.");
        }

        if (!StringUtils.hasLength(utilisateurDto.getMotDePasse())) {
            errors.add("Veuillez renseigner le 'mot de passe' de l'utilisateur.");
        }

        if (utilisateurDto.getAdresse() == null) {
            errors.add("Veuillez renseigner 'l'adresse' de l'utilisateur.");
        } else {

            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
                errors.add("Le champs 'Adresse 1' est obligatoire.");
            }

            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
                errors.add("Le champs 'Code Postale' est obligatoire.");
            }

            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
                errors.add("Le champs 'Ville' est obligatoire.");
            }

            if (!StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
                errors.add("Le champs 'Pays' est obligatoire.");
            }
        }

        return errors;
    }
}
