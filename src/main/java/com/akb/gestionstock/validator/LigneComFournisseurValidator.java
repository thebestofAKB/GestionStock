package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.LigneCommandeFournisseurDto;

import java.util.ArrayList;
import java.util.List;

public class LigneComFournisseurValidator {

    public static List<String> validate(LigneCommandeFournisseurDto ligneComFour) {
        List<String> errors = new ArrayList<>();

        if (ligneComFour == null) {
            errors.add("Veuillez selectionner l'Article de la ligne de commande");
            errors.add("Veuillez selectionner la commande client de la ligne de com");

            return errors;
        }

        if (ligneComFour.getArticleDto() == null) errors.add("Veuillez selectionner l'Article de la ligne de commande");

        if (ligneComFour.getCommandeFournisseurDto() == null) {
            errors.add("Veuillez selectionner la commande Fournisseur de la ligne de com");
        }

        return errors;
    }
}
