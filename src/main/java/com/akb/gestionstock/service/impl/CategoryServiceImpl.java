package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.CategoryDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.model.Categorie;
import com.akb.gestionstock.repository.CategoryRepository;
import com.akb.gestionstock.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto findById(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return null;
        }

        Optional<Categorie> categorie = categoryRepository.findById(id);

        return Optional.of(CategoryDto.fromEntity(categorie.get()))
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(
                                "Aucune Categorie avec l'ID %s n'a ete trouvee.", categorie),
                                ErrorCodes.CATEGORIE_NOT_FOUND)
                );
    }

    @Override
    public CategoryDto findByCode(String codeCategory) {

        if (!StringUtils.hasLength(codeCategory)) {
            log.error("Category CODE is null");
            return null;
        }

        Optional<Categorie> categorie = categoryRepository.findCategorieByCode(codeCategory);

        return Optional.of(CategoryDto.fromEntity(categorie.get()))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Aucune Categorie avec le CODE %s n'a ete trouvee.", codeCategory),
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public CategoryDto findByDesignation(String designation) {

        if (!StringUtils.hasLength(designation)) {
            log.error("Category DESGINATION is null");
            return null;
        }

        Optional<Categorie> categorie = categoryRepository.findCategorieByDesignation(designation);

        return Optional.of(CategoryDto.fromEntity(categorie.get()))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Aucune Categorie avec la DESIGNATION %s n'a ete trouvee.", designation),
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public List<CategoryDto> findAll() {
        return null;
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public CategoryDto update(CategoryDto categoryDto) {
        return null;
    }

    @Override
    public void delete(Integer id) {

    }
}
