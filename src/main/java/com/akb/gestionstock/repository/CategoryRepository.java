package com.akb.gestionstock.repository;

import com.akb.gestionstock.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Integer, Categorie> {
}
