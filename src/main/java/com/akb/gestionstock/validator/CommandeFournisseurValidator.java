package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.CommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class CommandeFournisseurValidator {

    public static List<String> validate(CommandeFournisseurDto cfdto) {
        List<String> errors = new ArrayList<>();

        if (cfdto == null || cfdto.getFournisseurDto() == null) {
            errors.add("Veuillez renseigner le fournisseur de la commande");
        }

        return errors;
    }

}
