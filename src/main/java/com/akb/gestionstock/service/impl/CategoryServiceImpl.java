package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.CategoryDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Categorie;
import com.akb.gestionstock.repository.CategoryRepository;
import com.akb.gestionstock.service.CategoryService;
import com.akb.gestionstock.validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

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

        return categoryRepository.findById(id)
                .map(CategoryDto::fromEntity)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                "Aucune Categorie avec l'ID " + id + " n'a ete trouvee.",
                                ErrorCodes.CATEGORIE_NOT_FOUND)
                );
    }

    @Override
    public CategoryDto findByCode(String codeCategory) {

        if (!StringUtils.hasLength(codeCategory)) {
            log.error("Category CODE is null");
            return null;
        }

        return categoryRepository.findCategorieByCode(codeCategory)
                .map(CategoryDto::fromEntity)
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

        return categoryRepository.findCategorieByDesignation(designation)
                .map(CategoryDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Aucune Categorie avec la DESIGNATION %s n'a ete trouvee.", designation),
                        ErrorCodes.CATEGORIE_NOT_FOUND
                ));
    }

    @Override
    public List<CategoryDto> findAll() {

        return categoryRepository.findAll().stream()
                .map(CategoryDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {

        List<String> errors = CategorieValidator.validate(categoryDto);

        if (!errors.isEmpty()) {
            log.error("Category is not valide {}", categoryDto);
            throw new InvalidEntityException(
                    String.format("La Categorie %s n'est pas valide", categoryDto),
                    ErrorCodes.CATEGORIE_NOT_VALID, errors);

        }

        Categorie savedCategory = categoryRepository.save(CategoryDto.toEntity(categoryDto));

        return CategoryDto.fromEntity(savedCategory);
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Category ID is null {}", id);
        }

        categoryRepository.deleteById(id);

    }
}
