package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.MouvementStockDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Article;
import com.akb.gestionstock.model.MouvementStock;
import com.akb.gestionstock.repository.ArticleRepository;
import com.akb.gestionstock.repository.MouvementStockRepository;
import com.akb.gestionstock.service.MouvementStockService;
import com.akb.gestionstock.validator.MouvementStockValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MouvementStockServiceImpl implements MouvementStockService {

    private MouvementStockRepository mouvementStockRepo;
    private ArticleRepository articleRepo;

    @Autowired
    public MouvementStockServiceImpl(MouvementStockRepository mouvementStockRepo, ArticleRepository articleRepo){
        this.mouvementStockRepo = mouvementStockRepo;
        this.articleRepo = articleRepo;
    }

    @Override
    public MouvementStockDto findById(Integer id) {

        if (id == null) {
            log.error("ID is null");
            return null;
        }

        return mouvementStockRepo.findById(id)
                .map(MouvementStockDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Aucun Mouvement de stock n'a ete trouve.",
                        ErrorCodes.MVT_STOCK_NOT_FOUND));
    }

    @Override
    public MouvementStockDto save(MouvementStockDto mouvementStockDto) {

        List<String> errors = MouvementStockValidator.validate(mouvementStockDto);

        if (!errors.isEmpty()) {
            log.error("MouvementStock is not valid");
            throw new InvalidEntityException("Ce mouvement de stock n'est pas valide",
                    ErrorCodes.MVT_STOCK_NOT_VALID, errors);
        }

        Optional<Article> article = articleRepo.findById(mouvementStockDto.getArticleDto().getId());

        if (article.isEmpty()){
            log.error("Article ID was not found in DB");
            throw new EntityNotFoundException("Aucun article avec l'ID "
                    + mouvementStockDto.getArticleDto().getId() + " n'a ete trouve",
                    ErrorCodes.ARTICLE_NOT_FOUND);
        }

        MouvementStock savedMvtStock = mouvementStockRepo.save(MouvementStockDto.toEntity(mouvementStockDto));

        return MouvementStockDto.fromEntity(savedMvtStock);
    }

    @Override
    public List<MouvementStockDto> findAll() {

        return mouvementStockRepo.findAll().stream()
                .map(MouvementStockDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("ID is null");
        }

        mouvementStockRepo.deleteById(id);
    }
}
