package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.LigneVente;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LigneVenteDto {

    private Integer id;

    private VenteDto vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;

    private ArticleDto articleDto;

    private Integer idEntreprise;

    public static LigneVenteDto fromEntity(LigneVente ligneVente) {

        if (ligneVente == null) {

            //TODO Throw an Exception
            return null;
        }

        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .vente(VenteDto.fromEntity(ligneVente.getVente()))
                .quantite(ligneVente.getQuantite())
                .articleDto(ArticleDto.fromEntity(ligneVente.getArticle()))
                .prixUnitaire(ligneVente.getPrixUnitaire())
                .idEntreprise(ligneVente.getIdEntreprise())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto ligneVenteDto) {

        if (ligneVenteDto == null) {

            //TODO Throw an Exception
            return null;
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(ligneVenteDto.getId());
        ligneVente.setVente(VenteDto.toEntity(ligneVenteDto.getVente()));
        ligneVente.setQuantite(ligneVenteDto.getQuantite());
        ligneVente.setPrixUnitaire(ligneVenteDto.getPrixUnitaire());
        ligneVente.setIdEntreprise(ligneVenteDto.getIdEntreprise());
        ligneVente.setArticle(ArticleDto.toEntity(ligneVenteDto.getArticleDto()));

        return ligneVente;
    }
}
