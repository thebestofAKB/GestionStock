package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.LigneCommandeClient;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LigneCommandeClientDto {

    private Integer id;

    private ArticleDto articleDto;

    private Integer idEntreprise;

    @JsonIgnore
    private CommandeClientDto commandeClientDto;

    public static LigneCommandeClientDto fromEntity(
            LigneCommandeClient ligneCommandeClient) {

        if (ligneCommandeClient == null) {

            //TODO Throw an Exception
            return null;
        }

        return LigneCommandeClientDto.builder()
                .id(ligneCommandeClient.getId())
                .articleDto(ArticleDto.fromEntity(ligneCommandeClient.getArticle()))
                .commandeClientDto(CommandeClientDto.
                        fromEntity(ligneCommandeClient.getCommandeClient()))
                .idEntreprise(ligneCommandeClient.getIdEntreprise())
                .build();
    }

    public static LigneCommandeClient toEntity(LigneCommandeClientDto ligneComDto) {

        if (ligneComDto == null) {

            //TODO Throw an Exception
            return null;
        }

        LigneCommandeClient ligneCom = new LigneCommandeClient();
        ligneCom.setId(ligneComDto.getId());
        ligneCom.setArticle(ArticleDto.toEntity(ligneComDto.getArticleDto()));
        ligneCom.setCommandeClient(CommandeClientDto
                .toEntity(ligneComDto.getCommandeClientDto()));
        ligneCom.setIdEntreprise(ligneComDto.getIdEntreprise());

        return ligneCom;
    }
}
