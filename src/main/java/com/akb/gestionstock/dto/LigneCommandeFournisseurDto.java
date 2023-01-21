package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.LigneCommandeFournisseur;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeFournisseurDto {

    private Integer id;

    private ArticleDto articleDto;

    private CommandeFournisseurDto commandeFournisseurDto;

    public static LigneCommandeFournisseurDto fromEntity(
            LigneCommandeFournisseur ligneComF) {

        if (ligneComF == null) {

            //TODO Throw an Exception
            return null;
        }

        return LigneCommandeFournisseurDto.builder()
                .id(ligneComF.getId())
                .articleDto(ArticleDto.fromEntity(ligneComF.getArticle()))
                .commandeFournisseurDto(CommandeFournisseurDto
                        .fromEntity(ligneComF.getCommandeFournisseur()))
                .build();
    }

    public static LigneCommandeFournisseur toEntity(
            LigneCommandeFournisseurDto ligneComFDto) {

        if (ligneComFDto == null) {

            //TODO Throw an Exception
            return null;
        }

        LigneCommandeFournisseur ligneComFr = new LigneCommandeFournisseur();
        ligneComFr.setId(ligneComFDto.getId());
        ligneComFr.setArticle(ArticleDto.toEntity(ligneComFDto.getArticleDto()));
        ligneComFr.setCommandeFournisseur(CommandeFournisseurDto
                .toEntity(ligneComFDto.getCommandeFournisseurDto()));

        return ligneComFr;
    }
}
