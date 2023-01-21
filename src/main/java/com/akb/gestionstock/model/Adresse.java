package com.akb.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Adresse {

    private String codePostale;

    private String adresse1;

    private String adresse2;

    private String ville;

    private String pays;
}
