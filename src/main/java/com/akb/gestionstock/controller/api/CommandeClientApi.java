package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.ClientDto;
import com.akb.gestionstock.dto.CommandeClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandeClients")
public interface CommandeClientApi {

    @GetMapping(value = APP_ROOT + "/commandeClients/{idComCli}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande Client par ID", notes = "Permet de chercher une commande client par son ID",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client avec cet ID n'a été trouvé dans la BDD")
    })
    ResponseEntity<CommandeClientDto> findById(@PathVariable("idComCli") Integer id);

    @GetMapping(value = APP_ROOT + "/commandeClients/{codeComCli}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande Client par CODE", notes = "Permet de chercher une commande client par son CODE",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune commande client avec ce CODE n'a été trouvé dans la BDD")
    })
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeComCli") String code);

    @PostMapping(value = APP_ROOT + "/commandeClients/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande client", notes = "Permet d'ajouter et/ou de modifier une commande client",
            response = CommandeClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande client a été créé/modifié"),
            @ApiResponse(code = 400, message = "L'objet commande client est invalide")
    })
    ResponseEntity<CommandeClientDto> save(@RequestBody CommandeClientDto commandeCltDto);

    @GetMapping(value = APP_ROOT + "/commandeClients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des commandes clients", notes = "Permet de chercher et renvoyer la liste des commandes clients " +
            "qui existent dans la BDD", responseContainer = "List<CommandeClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes clients / liste vide")
    })
    ResponseEntity<List<CommandeClientDto>> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandeClients/delete/{idComCli}")
    @ApiOperation(value = "Supprimer une commande client", notes = "Permet de supprimer une commande client dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande client a été supprimé")
    })
    ResponseEntity<?> delete(@PathVariable("idComCli") Integer id);

}
