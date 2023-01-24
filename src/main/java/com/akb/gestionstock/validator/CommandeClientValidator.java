package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.CommandeClientDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeClientValidator {

    public static List<String> validate(CommandeClientDto ccdto) {
        List<String> errors = new ArrayList<>();

        if (ccdto == null) {
            errors.add("Veuillez renseigner le code de la commande");
            errors.add("Veuillez renseigner la date de la commande");
            errors.add("Veuillez selectionner le client de la commande");

            return errors;
        }

        if (!StringUtils.hasLength(ccdto.getCode())) {
            errors.add("Veuillez renseigner le code de la commande");
        }

        if (ccdto.getDateCommande() == null) {
            errors.add("Veuillez renseigner la date de la commande");
        }

        if (ccdto.getClientDto() == null) {
            errors.add("Veuillez selectionner le client de la commande");
        }


        return errors;
    }
}
