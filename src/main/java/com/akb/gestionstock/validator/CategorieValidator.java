package com.akb.gestionstock.validator;

import com.akb.gestionstock.dto.CategorieDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategorieValidator {

    public static List<String> validate(CategorieDto categorieDto) {

        List<String> errors = new ArrayList<>();

        if (categorieDto == null || !StringUtils.hasLength(categorieDto.getCode())) {
            errors.add("Veuillez renseigner le code de la categorie");
        }

        return errors;
    }
}
