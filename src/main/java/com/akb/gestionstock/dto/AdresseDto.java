package com.akb.gestionstock.dto;

import com.akb.gestionstock.model.Adresse;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdresseDto {

    private Integer id;

    private String codePostale;

    private String adresse1;

    private String adresse2;

    private String ville;

    private String pays;

    public static AdresseDto fromEntity(Adresse adresse) {

        if (adresse == null) {
            //TODO Throw an exception
            return null;
        }

        return AdresseDto.builder()
                .codePostale(adresse.getCodePostale())
                .adresse1(adresse.getAdresse1())
                .adresse2(adresse.getAdresse2())
                .ville(adresse.getVille())
                .pays(adresse.getPays())
                .build();
    }

    public static Adresse toEntity(AdresseDto adresseDto) {

        if (adresseDto == null) {
            //TODO Throw an exception
            return null;
        }

        Adresse adresse = new Adresse();
        adresse.setCodePostale(adresseDto.getCodePostale());
        adresse.setAdresse1(adresseDto.getAdresse1());
        adresse.setAdresse2(adresseDto.getAdresse2());
        adresse.setVille(adresseDto.getVille());
        adresse.setPays(adresseDto.getPays());

        return adresse;
    }
}
