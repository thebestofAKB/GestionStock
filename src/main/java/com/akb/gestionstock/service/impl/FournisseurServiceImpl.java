package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.FournisseurDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Fournisseur;
import com.akb.gestionstock.repository.FournisseurRepository;
import com.akb.gestionstock.service.FournisseurService;
import com.akb.gestionstock.validator.FournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FournisseurServiceImpl implements FournisseurService {

    private FournisseurRepository fournisseurRepo;

    @Autowired
    public FournisseurServiceImpl(FournisseurRepository fournisseurRepo) {
        this.fournisseurRepo = fournisseurRepo;
    }

    @Override
    public FournisseurDto findById(Integer id) {

        if (id == null) {
            log.error("ID is null");
            return null;
        }

        return fournisseurRepo.findById(id)
                .map(FournisseurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("L'ID %d du fournisseur n'existe pas", id),
                        ErrorCodes.FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public FournisseurDto save(FournisseurDto fournisseurDto) {

        List<String> errors = FournisseurValidator.validate(fournisseurDto);

        if (!errors.isEmpty()) {
            log.error("Fournisseur is not valid {}", fournisseurDto);
            throw new InvalidEntityException("Ce Fournisseur n'est pas valide",
                    ErrorCodes.FOURNISSEUR_NOT_VALID, errors);
        }

        Fournisseur savedFournisseur = fournisseurRepo.save(FournisseurDto.toEntity(fournisseurDto));

        return FournisseurDto.fromEntity(savedFournisseur);
    }

    @Override
    public List<FournisseurDto> findAll() {

        return fournisseurRepo.findAll().stream()
                .map(FournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("ID is null");
        }

        fournisseurRepo.deleteById(id);
    }
}
