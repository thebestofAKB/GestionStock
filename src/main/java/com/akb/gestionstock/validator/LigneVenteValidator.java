package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {

    public static List<String> validate(LigneVenteDto ligneVenteDto) {
        List<String> errors = new ArrayList<>();

        if (ligneVenteDto == null) {
            errors.add("Veuillez selectionner la vente concernee");
            errors.add("Veuillez saisir la quantite");
            errors.add("Veuillez saisir le prix unitaire HT");

            return errors;
        }

        if (ligneVenteDto.getVente() == null) {
            errors.add("Veuillez selectionner la vente concernee");
        }

        if (ligneVenteDto.getQuantite() == null) {
            errors.add("Veuillez saisir la quantite");
        }

        if (ligneVenteDto.getPrixUnitaire() == null) {
            errors.add("Veuillez saisir le prix unitaire HT");
        }

        return errors;
    }
}
