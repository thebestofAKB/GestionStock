package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.MouvementStockDto;

import java.util.ArrayList;
import java.util.List;

public class MouvementStockValidator {

    public static List<String> validate(MouvementStockDto mvtStock) {
        List<String> errors = new ArrayList<>();

        if (mvtStock == null) {
            errors.add("Veuillez renseigner la date du mouvement");
            errors.add("Veuillez selectionner l'article concerne");
            errors.add("Veuillez renseigner la quantite du mouvement");

            return errors;
        }

        if (mvtStock.getDateMouvement() == null) {
            errors.add("Veuillez renseigner la date du mouvement");
        }

        if (mvtStock.getArticleDto() == null) {
            errors.add("Veuillez selectionner l'article concerne");
        }

        if (mvtStock.getQuantite() == null) {
            errors.add("Veuillez renseigner la quantite du mouvement");
        }

        return errors;
    }
}
