package com.akb.gestionstock.repository;

import com.akb.gestionstock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Categorie, Integer> {
    Optional<Categorie> findCategorieByCode(String codeCategory);

    Optional<Categorie> findCategorieByDesignation(String designation);
}
