package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.CommandeFournisseurDto;
import com.akb.gestionstock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/commandeFournisseurs")
public interface CommandeFournisseurApi {

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/{idComFour}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une commande fournisseur par ID", notes = "Permet de chercher une commande fournisseur par son ID",
            response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur a été trouvé"),
            @ApiResponse(code = 404, message = "Aucune commande fournisseur avec cet ID n'a été trouvé dans la BDD")
    })
    CommandeFournisseurDto findById(@PathVariable("idComFour") Integer id);

    @PostMapping(value = APP_ROOT + "/commandeFournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une commande fournisseur", notes = "Permet d'ajouter et/ou de modifier une commande fournisseur",
            response = CommandeFournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet commande fournisseur a été créé/modifié"),
            @ApiResponse(code = 400, message = "L'objet commande fournisseur est invalide")
    })
    CommandeFournisseurDto save(@RequestBody CommandeFournisseurDto commandeFourDto);

    @GetMapping(value = APP_ROOT + "/commandeFournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des commandes fournisseurs", notes = "Permet de chercher et renvoyer la liste des commandes fournisseurs " +
            "qui existent dans la BDD", responseContainer = "List<CommandeFournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des commandes fournisseurs / liste vide")
    })
    List<CommandeFournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/commandeFournisseurs/delete/{idComFour}")
    @ApiOperation(value = "Supprimer une commande fournisseur", notes = "Permet de supprimer une commande fournisseur dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La commande fournisseur a été supprimé")
    })
    void delete(@PathVariable("idComFour") Integer id);
}
