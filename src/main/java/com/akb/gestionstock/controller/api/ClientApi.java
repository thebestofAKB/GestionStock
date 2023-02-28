package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.ClientDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/clients")
public interface ClientApi {

    @GetMapping(value = APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par ID", notes = "Permet de chercher un client par son ID",
            response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun client avec cet ID n'a été trouvé dans la BDD")
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @PostMapping(value = APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client", notes = "Permet d'ajouter et/ou de modifier un client",
            response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client a été créé/modifié"),
            @ApiResponse(code = 400, message = "L'objet client est invalide")
    })
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value = APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des clients", notes = "Permet de chercher et renvoyer la liste des clients " +
            "qui existent dans la BDD", responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des clients / liste vide")
    })
    List<ClientDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/clients/delete/{idClient}")
    @ApiOperation(value = "Supprimer un client", notes = "Permet de supprimer un client dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a été supprimé")
    })
    void delete(@PathVariable("idClient") Integer id);

}
