package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.FournisseurDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/fournisseurs")
public interface FournisseurApi {

    @GetMapping(value = APP_ROOT + "/fournisseurs/{idFournisseur}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un fournisseur par ID", notes = "Permet de chercher un fournisseur par son ID",
            response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun fournisseur avec cet ID n'a été trouvé dans la BDD")
    })
    FournisseurDto findById(@PathVariable("idFournisseur") Integer id);

    @PostMapping(value = APP_ROOT + "/fournisseurs/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un fournisseur", notes = "Permet d'ajouter et/ou de modifier un fournisseur",
            response = FournisseurDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet fournisseur a été créé/modifié"),
            @ApiResponse(code = 400, message = "L'objet fournisseur est invalide")
    })
    FournisseurDto save(@RequestBody FournisseurDto fournisseurDto);

    @GetMapping(value = APP_ROOT + "/fournisseurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des fournisseurs", notes = "Permet de chercher et renvoyer la liste des fournisseurs " +
            "qui existent dans la BDD", responseContainer = "List<FournisseurDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des fournisseurs / liste vide")
    })
    List<FournisseurDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/fournisseurs/delete/{idFournisseur}")
    @ApiOperation(value = "Supprimer un fournisseur", notes = "Permet de supprimer un fournisseur dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le fournisseur a été supprimé")
    })
    void delete(@PathVariable("idFournisseur") Integer id);

}
