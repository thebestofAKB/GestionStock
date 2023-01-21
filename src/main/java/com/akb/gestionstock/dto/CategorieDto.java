package com.akb.gestionstock.dto;

import java.util.List;

import com.akb.gestionstock.model.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CategorieDto {

    private Integer id;

    private String code;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> articles;

    public static CategorieDto fromEntity(Categorie categorie) {

        if (categorie == null) {

            //TODO Throw an Exception
            return null;
        }

        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .build();
    }

    public static Categorie toEntity(CategorieDto categorieDto) {

        if(categorieDto == null) {
            //TODO Throw an Exception
            return null;
        }

        Categorie categorie = new Categorie();
        categorie.setId(categorieDto.getId());
        categorie.setCode(categorieDto.getCode());
        categorie.setDesignation(categorieDto.getDesignation());

        return categorie;
    }
}
