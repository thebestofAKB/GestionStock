package com.akb.gestionstock.repository;

import com.akb.gestionstock.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Integer, Article> {
}
