package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.LigneVenteDto;
import com.akb.gestionstock.dto.VenteDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Article;
import com.akb.gestionstock.model.LigneVente;
import com.akb.gestionstock.model.Vente;
import com.akb.gestionstock.repository.ArticleRepository;
import com.akb.gestionstock.repository.LigneVenteRepository;
import com.akb.gestionstock.repository.VenteRepository;
import com.akb.gestionstock.service.VenteService;
import com.akb.gestionstock.validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {

    private VenteRepository venteRepository;
    private ArticleRepository articleRepository;
    private LigneVenteRepository ligneVenteRepository;

    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository,
                            ArticleRepository articleRepository,
                            LigneVenteRepository ligneVenteRepository) {

        this.venteRepository = venteRepository;
        this.articleRepository = articleRepository;
        this.ligneVenteRepository = ligneVenteRepository;
    }

    @Override
    public VenteDto findById(Integer id) {

        if (id == null) {
            log.error("ID is null");
            return null;
        }

        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("La vente avec l'ID %d n'a pas ete trouve", id),
                        ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public VenteDto findByCode(String code) {

        if (!StringUtils.hasLength(code)){
            log.error("CODE is null");
            return null;
        }

        return venteRepository.findVenteByCode(code)
                .map(VenteDto::fromEntity)
                .orElseThrow(()->new EntityNotFoundException(
                        String.format("La vente avec le CODE %s n'a pas ete trouve", code),
                        ErrorCodes.VENTE_NOT_FOUND
                ));
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public VenteDto save(VenteDto venteDto) {

        List<String> errors = VenteValidator.validate(venteDto);

        if (!errors.isEmpty()){
            log.error("Vente is not valid");
            throw new InvalidEntityException("Vente n'est pas valide",
                    ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        venteDto.getLigneVente().forEach(ligneVente -> {
            Optional<Article> article = articleRepository.findById(ligneVente.getArticle().getId());
            if (article.isEmpty()){
                articleErrors.add(String.format("Aucun article avec l'ID %d n'a ete trouve dans la base",
                        ligneVente.getArticle().getId()));
            }

        });

        if (!articleErrors.isEmpty()){
            log.error("One or more articles were not found in the Database");
            throw new InvalidEntityException("Un ou plusieurs articles n'ont pas ete trouve dans la BDD",
                    ErrorCodes.VENTE_NOT_VALID, articleErrors);
        }

        Vente savedVente = venteRepository.save(VenteDto.toEntity(venteDto));

        venteDto.getLigneVente().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(savedVente);
            ligneVenteRepository.save(ligneVente);

        });

        return VenteDto.fromEntity(savedVente);
    }

    @Override
    public void delete(Integer id) {
        if (id == null){
            log.error("ID is null");
        }

        venteRepository.deleteById(id);
    }
}
