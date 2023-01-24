package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.EntrepriseDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseValidator {

    public static List<String> validate(EntrepriseDto entrepriseDto) {
        List<String> errors = new ArrayList<>();

        if (entrepriseDto == null) {
            errors.add("Veuillez renseigner le nom de l'entreprise.");
            errors.add("Veuillez renseigner la description de l'entreprise.");
            errors.add("Veuillez renseigner le code fiscal de l'entreprise.");

            return errors;
        }

        if (!StringUtils.hasLength(entrepriseDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'entreprise.");
        }

        if (!StringUtils.hasLength(entrepriseDto.getDescription())) {
            errors.add("Veuillez renseigner la description de l'entreprise.");
        }

        if (!StringUtils.hasLength(entrepriseDto.getCodeFiscal())) {
            errors.add("Veuillez renseigner le code fiscal de l'entreprise.");
        }

        return errors;
    }
}
