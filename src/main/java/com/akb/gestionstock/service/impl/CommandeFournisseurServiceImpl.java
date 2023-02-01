package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.CommandeFournisseurDto;
import com.akb.gestionstock.dto.LigneCommandeFournisseurDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Article;
import com.akb.gestionstock.model.CommandeFournisseur;
import com.akb.gestionstock.model.Fournisseur;
import com.akb.gestionstock.model.LigneCommandeFournisseur;
import com.akb.gestionstock.repository.ArticleRepository;
import com.akb.gestionstock.repository.CommandeFournisseurRepository;
import com.akb.gestionstock.repository.FournisseurRepository;
import com.akb.gestionstock.repository.LigneCommandeFournisseurRepository;
import com.akb.gestionstock.service.CommandeFournisseurService;
import com.akb.gestionstock.validator.CommandeFournisseurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private CommandeFournisseurRepository commandeFourRepo;
    private FournisseurRepository fournisseurRepository;
    private LigneCommandeFournisseurRepository ligneComFourRepo;
    private ArticleRepository articleRepo;


    @Autowired
    public CommandeFournisseurServiceImpl(CommandeFournisseurRepository commandeFourRepo,
                                          FournisseurRepository fournisseurRepository,
                                          LigneCommandeFournisseurRepository ligneComFourRepo,
                                          ArticleRepository articleRepo) {

        this.commandeFourRepo = commandeFourRepo;
        this.fournisseurRepository = fournisseurRepository;
        this.ligneComFourRepo = ligneComFourRepo;
        this.articleRepo = articleRepo;
    }

    @Override
    public CommandeFournisseurDto findById(Integer id) {

        if (id == null){
            log.error("ID is null");
            throw new EntityNotFoundException("La commande n'a pas ete trouvee", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }

        return commandeFourRepo.findById(id)
                .map(CommandeFournisseurDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "La Commande client n'a pas ete trouvee",
                        ErrorCodes.COMMANDE_FOURNISSEUR_NOT_FOUND
                ));
    }

    @Override
    public CommandeFournisseurDto save(CommandeFournisseurDto commandeFourDto) {

        List<String> errors = CommandeFournisseurValidator.validate(commandeFourDto);

        if (!errors.isEmpty()) {
            log.error("Commande Client is not valid {}", commandeFourDto);
            throw new InvalidEntityException("La commande n'est pas valide",
                    ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Fournisseur> fournisseur =
                fournisseurRepository.findById(commandeFourDto.getFournisseurDto().getId());

        if (fournisseur.isEmpty()) {
            log.warn("Fournisseur with ID {} was not found in DB", commandeFourDto.getFournisseurDto().getId());
            throw new InvalidEntityException("Aucun Fournisseur avec l'ID "
                    + commandeFourDto.getFournisseurDto().getId()
                    + " n'a ete trouve dans la base de donnees",
                    ErrorCodes.FOURNISSEUR_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeFourDto.getLigneCommandeFournisseurs() != null) {
            commandeFourDto.getLigneCommandeFournisseurs().forEach(ligneComFour -> {
                if (ligneComFour.getArticleDto() != null) {

                    Optional<Article> article = articleRepo.findById(ligneComFour.getArticleDto().getId());

                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID "
                                + ligneComFour.getArticleDto().getId()
                                + " n'a pas ete trouve dans la base de donnees");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL.");
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.error("Article was not found");
            throw new InvalidEntityException("l'Article n'existe pas dans la base",
                    ErrorCodes.ARTICLE_NOT_FOUND, articleErrors);
        }

        CommandeFournisseur savedComFour = commandeFourRepo.save(CommandeFournisseurDto.toEntity(commandeFourDto));

        if (commandeFourDto.getLigneCommandeFournisseurs() != null) {
            commandeFourDto.getLigneCommandeFournisseurs().forEach(ligneComFour -> {
                LigneCommandeFournisseur ligneCommandeFour = LigneCommandeFournisseurDto.toEntity(ligneComFour);

                if (ligneCommandeFour != null) {
                    ligneCommandeFour.setCommandeFournisseur(savedComFour);
                }

                ligneComFourRepo.save(ligneCommandeFour);
            });
        }

        return CommandeFournisseurDto.fromEntity(savedComFour);
    }

    @Override
    public List<CommandeFournisseurDto> findAll() {

        return commandeFourRepo.findAll().stream()
                .map(CommandeFournisseurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("ID is null");
        }

        commandeFourRepo.deleteById(id);
    }
}
