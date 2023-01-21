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
@Table(name = "client")
public class Client extends AbstractEntity {

    private String nom;

    private String prenom;

    @Embedded
    private Adresse adresse;

    private String photo;

    private String email;

    private String telephone;

    @OneToMany(mappedBy = "client")
    private List<CommandeClient> commandeClients;
}
