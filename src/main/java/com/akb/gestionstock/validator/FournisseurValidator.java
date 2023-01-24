package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.ClientDto;
import com.akb.gestionstock.dto.FournisseurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class FournisseurValidator {

    public static List<String> validate(FournisseurDto fournisseurDto) {

        List<String> errors = new ArrayList<>();


        if (fournisseurDto == null) {
            errors.add("Veuillez renseigner le nom du fournisseur");
            errors.add("Veuillez renseigner le prenom du fournisseur");
            errors.add("Veuillez renseigner l'E-mail du fournisseur");
            errors.add("Veuillez renseigner le numero de telephone du fournisseur");

            return errors;
        }

        if (!StringUtils.hasLength(fournisseurDto.getNom())) {
            errors.add("Veuillez renseigner le nom du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getEmail())) {
            errors.add("Veuillez renseigner l'E-mail du fournisseur");
        }

        if (!StringUtils.hasLength(fournisseurDto.getTelephone())) {
            errors.add("Veuillez renseigner le numero de telephone du fournisseur");
        }

        return errors;
    }

}
