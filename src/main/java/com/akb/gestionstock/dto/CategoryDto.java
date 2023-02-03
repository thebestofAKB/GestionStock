package com.akb.gestionstock.dto;

import java.util.List;

import com.akb.gestionstock.model.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Builder;

@Data
@Builder
public class CategoryDto {

    private Integer id;

    private String code;

    private String designation;

    @JsonIgnore
    private List<ArticleDto> articles;

    private Integer idEntreprise;

    public static CategoryDto fromEntity(Categorie categorie) {

        if (categorie == null) {

            //TODO Throw an Exception
            return null;
        }

        return CategoryDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .designation(categorie.getDesignation())
                .idEntreprise(categorie.getIdEntreprise())
                .build();
    }

    public static Categorie toEntity(CategoryDto categoryDto) {

        if(categoryDto == null) {
            //TODO Throw an Exception
            return null;
        }

        Categorie categorie = new Categorie();
        categorie.setId(categoryDto.getId());
        categorie.setCode(categoryDto.getCode());
        categorie.setDesignation(categoryDto.getDesignation());
        categorie.setIdEntreprise(categoryDto.getIdEntreprise());

        return categorie;
    }
}
