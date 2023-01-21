package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.MouvementStock;
import com.akb.gestionstock.model.TypeMouvementStock;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class MouvementStockDto {

    private Integer id;

    private Instant dateMouvement;

    private ArticleDto articleDto;

    private BigDecimal quantite;

    private TypeMouvementStock typeMouvementStock;

    public static MouvementStockDto fromEntity(MouvementStock mouvementStock) {

        if (mouvementStock == null) {

            //TODO Throw an Exception
            return null;
        }

        return MouvementStockDto.builder()
                .id(mouvementStock.getId())
                .dateMouvement(mouvementStock.getDateMouvement())
                .articleDto(ArticleDto.fromEntity(mouvementStock.getArticle()))
                .quantite(mouvementStock.getQuantite())
                .build();
    }

    public static MouvementStock toEntity(MouvementStockDto mouvementStockDto) {

        if (mouvementStockDto == null) {

            //TODO Throw an Exception
            return null;
        }

        MouvementStock mouvementStock = new MouvementStock();
        mouvementStock.setId(mouvementStockDto.getId());
        mouvementStock.setDateMouvement(mouvementStockDto.getDateMouvement());
        mouvementStock.setArticle(ArticleDto
                .toEntity(mouvementStockDto.getArticleDto()));
        mouvementStock.setQuantite(mouvementStockDto.getQuantite());

        return mouvementStock;
    }
}
