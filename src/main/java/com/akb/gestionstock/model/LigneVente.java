package com.akb.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ligneVente")
public class LigneVente extends AbstractEntity {

    private Integer idEntreprise;

    @ManyToOne
    @JoinColumn(name = "idVente")
    private Vente vente;

    private BigDecimal quantite;

    private BigDecimal prixUnitaire;
}
