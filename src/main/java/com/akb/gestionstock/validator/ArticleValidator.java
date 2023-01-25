package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.ArticleDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleValidator {

    public static List<String> validate(ArticleDto articleDto) {
        List<String> errors = new ArrayList<>();

        if (articleDto == null) {
            errors.add("Veuillez renseigner le code de l'article");
            errors.add("Veuillez renseigner la désignation de l'article");
            errors.add("Veuillez renseigner le prix unitaire HT de l'article");
            errors.add("Veuillez renseigner le taux TVA de l'article");
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
            errors.add("Veuillez selectionnez une categorie");
        }

        if (!StringUtils.hasLength(articleDto.getCodeArticle())) {
            errors.add("Veuillez renseigner le code de l'article");
        }

        if (!StringUtils.hasLength(articleDto.getDesignation())) {
            errors.add("Veuillez renseigner la désignation de l'article");
        }

        if (articleDto.getPrixUnitaireHt() == null) {
            errors.add("Veuillez renseigner le prix unitaire HT de l'article");
        }

        if (articleDto.getTauxTva() == null) {
            errors.add("Veuillez renseigner le taux TVA de l'article");
        }

        if (articleDto.getPrixUnitaireTtc() == null) {
            errors.add("Veuillez renseigner le prix unitaire TTC de l'article");
        }

        if (articleDto.getCategoryDto() == null) {
            if (articleDto.getPrixUnitaireHt() == null) {
                errors.add("Veuillez selectionnez une categorie");
            }
        }


        return errors;
    }
}