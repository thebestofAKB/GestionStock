package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.CategorieDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategorieApi {

    @PostMapping(value = APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une categorie", notes = "Permet d'ajouter et/ou de modifier une categorie",
            response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a été créé/modifiée"),
            @ApiResponse(code = 400, message = "L'objet categorie est invalide")
    })
    CategorieDto save(@RequestBody CategorieDto categorieDto);

    @GetMapping(value = APP_ROOT + "/categories/{idCategorie}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un categorie par ID", notes = "Permet de chercher une categorie par son ID",
            response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie avec cet ID n'a été trouvé dans la BDD")
    })
    CategorieDto findById(@PathVariable("idCategorie") Integer id);

    @GetMapping(value = APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par CODE", notes = "Permet de chercher une categorie par son CODE",
            response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie avec ce CODE n'a été trouvé dans la BDD")
    })
    CategorieDto findByCode(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value = APP_ROOT + "/categories/{designation}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une categorie par DESIGNATION", notes = "Permet de chercher une categorie par son DESIGNATION",
            response = CategorieDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet categorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie avec cette DESIGNATION n'a été trouvé dans la BDD")
    })
    CategorieDto findByDesignation(@PathVariable("designation") String designation);

    @GetMapping(value = APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des categories", notes = "Permet de chercher et renvoyer la liste des categories " +
            "qui existent dans la BDD", responseContainer = "List<CategorieDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / liste vide")
    })
    List<CategorieDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/categories/delete/{idCategorie}")
    @ApiOperation(value = "Supprimer un categorie", notes = "Permet de supprimer un categorie dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a été supprimé")
    })
    void delete(@PathVariable("idCategorie") Integer id);
}
