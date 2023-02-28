package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.UtilisateurDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.repository.UtilisateurRepository;
import com.akb.gestionstock.service.UtilisateurService;
import com.akb.gestionstock.validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {

    private UtilisateurRepository userRepo;

    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UtilisateurDto findById(Integer id) {

        if (id == null) {
            log.error("ID is null");
            return null;
        }

        return userRepo.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'ID " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public UtilisateurDto findByEmail(String email) {

        if (StringUtils.hasLength(email)) {
            log.error("EMAIL is null");
            return null;
        }

        return userRepo.findByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'EMAIL " + email + " n'a ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {

        List<String> errors = UtilisateurValidator.validate(utilisateurDto);

        if (!errors.isEmpty()){
            log.error("Utilisateur is not Valid");
            throw new InvalidEntityException("Cet Utilisateur n'est pas valide",
                    ErrorCodes.UTILISATEUR_NOT_VALID, errors);
        }

        return UtilisateurDto.fromEntity(
                userRepo.save(
                        UtilisateurDto.toEntity(utilisateurDto)
                )
        );
    }

    @Override
    public List<UtilisateurDto> findAll() {

        return userRepo.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null){
            log.error("ID is null");
        }

        userRepo.deleteById(id);
    }
}
