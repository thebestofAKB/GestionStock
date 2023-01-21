package com.akb.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "entreprise")
public class Entreprise extends AbstractEntity {

    private String nom;

    private String description;

    @Embedded
    private Adresse adresse;

    private String codeFiscal;

    private String photo;

    @OneToMany
    private List<Utilisateur> utilisateurs;


}
