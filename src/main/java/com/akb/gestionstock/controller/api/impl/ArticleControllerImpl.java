package com.akb.gestionstock.controller.api.impl;

import com.akb.gestionstock.controller.api.ArticleControllerApi;
import com.akb.gestionstock.dto.ArticleDto;
import com.akb.gestionstock.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(name = APP_ROOT)
public class ArticleControllerImpl implements ArticleControllerApi {

    private ArticleService articleService;

    @Autowired
    public ArticleControllerImpl(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public ArticleDto findById(Integer id) {
        return articleService.findById(id);
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return articleService.findByCodeArticle(codeArticle);
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        return articleService.save(articleDto);
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleService.findAll();
    }

    @Override
    public void delete(Integer id) {
        articleService.delete(id);
    }
}
