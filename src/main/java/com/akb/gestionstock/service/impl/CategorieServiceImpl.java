package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.CategorieDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Categorie;
import com.akb.gestionstock.repository.CategoryRepository;
import com.akb.gestionstock.service.CategorieService;
import com.akb.gestionstock.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieServiceImpl implements CategorieService {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategorieServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategorieDto findById(Integer id) {

        if (id == null) {
            log.error("Category ID is null");
            return null;
        }

        return categoryRepository.findById(id)
                .map(CategorieDto::fromEntity)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Aucune Categorie avec l'ID " + id + " n'a ete trouvee.",
                                ErrorCodes.CATEGORIE_NOT_FOUND)
                );
    }

    @Override
    public CategorieDto findByCode(String codeCategory) {

        if (!StringUtils.hasLength(codeCategory)) {
            log.error("Category CODE is null");
            return null;
        }

        return categoryRepository.findCategorieByCode(codeCategory)
                .map(CategorieDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Aucune Categorie avec le CODE %s n'a ete trouvee.", codeCategory),
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public CategorieDto findByDesignation(String designation) {

        if (!StringUtils.hasLength(designation)) {
            log.error("Category DESGINATION is null");
            return null;
        }

        return categoryRepository.findCategorieByDesignation(designation)
                .map(CategorieDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Aucune Categorie avec la DESIGNATION %s n'a ete trouvee.", designation),
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public List<CategorieDto> findAll() {

        return categoryRepository.findAll().stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategorieDto save(CategorieDto categorieDto) {

        List<String> errors = CategorieValidator.validate(categorieDto);

        if (!errors.isEmpty()) {
            log.error("Category is not valide {}", categorieDto);
            throw new InvalidEntityException(
                    String.format("La Categorie %s n'est pas valide", categorieDto),
                    ErrorCodes.CATEGORIE_NOT_VALID, errors);

        }

        Categorie savedCategory = categoryRepository.save(CategorieDto.toEntity(categorieDto));

        return CategorieDto.fromEntity(savedCategory);
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Category ID is null {}", id);
        }

        categoryRepository.deleteById(id);

    }
}
