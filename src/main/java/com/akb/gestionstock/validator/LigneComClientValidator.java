package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.LigneCommandeClientDto;

import java.util.ArrayList;
import java.util.List;

public class LigneComClientValidator {

    public static List<String> validate(LigneCommandeClientDto ligneComCli) {
        List<String> errors = new ArrayList<>();

        if (ligneComCli == null) {
            errors.add("Veuillez selectionner l'Article de la ligne de commande");
            errors.add("Veuillez selectionner la commande client de la ligne de com");

            return errors;
        }

        if (ligneComCli.getArticleDto() == null) {
            errors.add("Veuillez selectionner l'Article de la ligne de commande");
        }

        if (ligneComCli.getCommandeClientDto() == null) {
            errors.add("Veuillez selectionner la commande client de la ligne de com");
        }

        return errors;
    }
}
