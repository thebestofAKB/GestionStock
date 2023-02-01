package com.akb.gestionstock.service.impl;

import com.akb.gestionstock.dto.CommandeClientDto;
import com.akb.gestionstock.dto.LigneCommandeClientDto;
import com.akb.gestionstock.exception.EntityNotFoundException;
import com.akb.gestionstock.exception.ErrorCodes;
import com.akb.gestionstock.exception.InvalidEntityException;
import com.akb.gestionstock.model.Article;
import com.akb.gestionstock.model.Client;
import com.akb.gestionstock.model.CommandeClient;
import com.akb.gestionstock.model.LigneCommandeClient;
import com.akb.gestionstock.repository.ArticleRepository;
import com.akb.gestionstock.repository.ClientRepository;
import com.akb.gestionstock.repository.CommandeClientRepository;
import com.akb.gestionstock.repository.LigneCommandeClientRepository;
import com.akb.gestionstock.service.CommandeClientService;
import com.akb.gestionstock.validator.CommandeClientValidator;
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
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeCltRepo;
    private ClientRepository clientRepository;
    private LigneCommandeClientRepository ligneComCltRepo;
    private ArticleRepository articleRepo;


    @Autowired
    public CommandeClientServiceImpl(CommandeClientRepository commandeCltRepo,
                                     ClientRepository clientRepository,
                                     LigneCommandeClientRepository ligneComCltRepo,
                                     ArticleRepository articleRepo) {

        this.commandeCltRepo = commandeCltRepo;
        this.clientRepository = clientRepository;
        this.ligneComCltRepo = ligneComCltRepo;
        this.articleRepo = articleRepo;
    }

    @Override
    public CommandeClientDto findById(Integer id) {

        if (id == null){
            log.error("ID is null");
            throw new EntityNotFoundException("La commande n'a pas ete trouvee", ErrorCodes.COMMANDE_CLIENT_NOT_FOUND);
        }

        return commandeCltRepo.findById(id)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "La Commande client n'a pas ete trouvee",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {

        if (!StringUtils.hasLength(code)){
            log.error("Commande Client CODE is NULL");
            return null;
        }

        return commandeCltRepo.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException(
                        "La commande client n'a pas ete trouvee dans la BDD",
                        ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeCltDto) {

        List<String> errors = CommandeClientValidator.validate(commandeCltDto);

        if (!errors.isEmpty()) {
            log.error("Commande Client is not valid {}", commandeCltDto);
            throw new InvalidEntityException("La commande n'est pas valide",
                    ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client =
                clientRepository.findById(commandeCltDto.getClientDto().getId());

        if (client.isEmpty()) {
            log.warn("Client with ID {} was not found in DB", commandeCltDto.getClientDto().getId());
            throw new InvalidEntityException("Aucun client avec l'ID "
                    + commandeCltDto.getClientDto().getId()
                    + " n'a ete trouve dans la base de donnees",
                    ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if (commandeCltDto.getLigneCommandeClients() != null) {
            commandeCltDto.getLigneCommandeClients().forEach(ligneComClt -> {
                if (ligneComClt.getArticleDto() != null) {

                    Optional<Article> article = articleRepo.findById(ligneComClt.getArticleDto().getId());

                    if (article.isEmpty()) {
                        articleErrors.add("L'article avec l'ID "
                                + ligneComClt.getArticleDto().getId()
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

        CommandeClient savedComClt = commandeCltRepo.save(CommandeClientDto.toEntity(commandeCltDto));

        if (commandeCltDto.getLigneCommandeClients() != null) {
            commandeCltDto.getLigneCommandeClients().forEach(ligneComClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligneComClt);

                if (ligneCommandeClient != null) {
                    ligneCommandeClient.setCommandeClient(savedComClt);
                }

                ligneComCltRepo.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(savedComClt);
    }

    @Override
    public List<CommandeClientDto> findAll() {

        return commandeCltRepo.findAll().stream()
                .map(CommandeClientDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("ID is null");
        }

        commandeCltRepo.deleteById(id);
    }
}
