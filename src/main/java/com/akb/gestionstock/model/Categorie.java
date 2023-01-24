package com.akb.gestionstock.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categorie")
public class Categorie extends AbstractEntity {

    private String code;

    private String designation;

    private Integer idEntreprise;

    @OneToMany(mappedBy = "categorie")
    private List<Article> articles;
}
