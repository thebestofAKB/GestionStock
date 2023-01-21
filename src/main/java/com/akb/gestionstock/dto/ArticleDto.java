package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.Article;
import com.akb.gestionstock.model.Categorie;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ArticleDto {

    private Integer id;

    private String codeArticle;

    private String designation;

    private BigDecimal prixUnitaireHt;

    private BigDecimal tauxTva;

    private BigDecimal prixUnitaireTtc;

    private String photo;

    private CategorieDto categorieDto;


    public static ArticleDto fromEntity(Article article) {

        if (article == null) {
            //TODO Throw an exception
            return null;
        }

        return ArticleDto.builder()
                .id(article.getId())
                .codeArticle(article.getCodeArticle())
                .designation(article.getDesignation())
                .prixUnitaireHt(article.getPrixUnitaireHt())
                .tauxTva(article.getTauxTva())
                .prixUnitaireTtc(article.getPrixUnitaireTtc())
                .photo(article.getPhoto())
                .categorieDto(CategorieDto.fromEntity(article.getCategorie()))
                .build();
    }

    public static Article toEntity(ArticleDto articleDto) {

        if (articleDto == null) {
            //TODO Throw an exception
            return null;
        }

        Article article = new Article();
        article.setCodeArticle(articleDto.getCodeArticle());
        article.setDesignation(articleDto.getDesignation());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setTauxTva(articleDto.getTauxTva());
        article.setPrixUnitaireHt(articleDto.getPrixUnitaireHt());
        article.setPrixUnitaireTtc(articleDto.getPrixUnitaireTtc());
        article.setPhoto(articleDto.getPhoto());
        article.setCategorie(CategorieDto.toEntity(articleDto.getCategorieDto()));

        return article;
    }

}
