package com.akb.gestionstock.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode()
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TypeMouvementStock")
public class TypeMouvementStock extends AbstractEntity {

    private String entreesStock;

    private String sortiesStock;
}
