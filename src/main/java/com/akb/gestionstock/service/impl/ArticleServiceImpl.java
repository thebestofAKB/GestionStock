package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.ArticleDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Article;
import com.akb.gestionstock.repository.ArticleRepository;
import com.akb.gestionstock.service.ArticleService;
import com.akb.gestionstock.validator.ArticleValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public ArticleDto findById(Integer id) {

        if (id == null) {
            log.error("Article ID is null");
            return null;
        }

        return articleRepository.findById(id)
                .map(ArticleDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun article avec l'id " + id + " n'a été trouvé",
                        ErrorCodes.ARTICLE_NOT_FOUND));
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {

        if (!StringUtils.hasLength(codeArticle)) {
            log.error("Article CODE is null");
            return null;
        }

        return articleRepository.findArticleByCodeArticle(codeArticle)
                .map(ArticleDto::fromEntity)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Aucun article avec le CODE " + codeArticle + " n'a été trouvé",
                                ErrorCodes.ARTICLE_NOT_FOUND)
                );
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {

        List<String> errors = ArticleValidator.validate(articleDto);

        if (!errors.isEmpty()) {
            log.error("Article is not valid {}", articleDto);
            throw new InvalidEntityException("L'article n'est pas valide", ErrorCodes.ARTICLE_NOT_VALID, errors);
        }

        return ArticleDto.fromEntity(
                articleRepository.save(
                        ArticleDto.toEntity(articleDto)
                )
        );
    }

    @Override
    public List<ArticleDto> findAll() {

        return articleRepository.findAll().stream()
                .map(ArticleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Article ID is null");
        }

        articleRepository.deleteById(id);
    }
}
