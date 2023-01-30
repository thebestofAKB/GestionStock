package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.EntrepriseDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Entreprise;
import com.akb.gestionstock.repository.EntrepriseRepository;
import com.akb.gestionstock.service.EntrepriseService;
import com.akb.gestionstock.validator.EntrepriseValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepo;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepo) {
        this.entrepriseRepo = entrepriseRepo;
    }

    @Override
    public EntrepriseDto findById(Integer id) {

        if (id == null) {
            log.error("ID is null");
            return null;
        }

        return entrepriseRepo.findById(id)
                .map(EntrepriseDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Cette entreprise n'a pas ete trouvee",
                        ErrorCodes.ENTREPRISE_NOT_FOUND
                ));
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {

        List<String> errors = EntrepriseValidator.validate(entrepriseDto);

        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {}", entrepriseDto);
            throw new InvalidEntityException(
                    String.format("L'Entreprise %s n'est pas valide", entrepriseDto),
                    ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }

        Entreprise savedEntreprise = entrepriseRepo.save(EntrepriseDto.toEntity(entrepriseDto));

        return EntrepriseDto.fromEntity(savedEntreprise);
    }

    @Override
    public List<EntrepriseDto> findAll() {

        return entrepriseRepo.findAll().stream()
                .map(EntrepriseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("ID is null");
        }

        entrepriseRepo.deleteById(id);
    }
}
