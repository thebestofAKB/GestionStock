package com.akb.gestionstock.service;

import com.akb.gestionstock.dto.ArticleDto;

import java.util.List;

public interface ArticleService {

    ArticleDto findById(Integer id);

    ArticleDto findByCodeArticle(String codeArticle);

    ArticleDto save(ArticleDto articleDto);

    List<ArticleDto> findAll();

    void delete(Integer id);
}
