package com.akb.gestionstock.repository;

import com.akb.gestionstock.model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Integer> {
}
