package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.ArticleDto;
import com.akb.gestionstock.dto.VenteDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/ventes")
public interface VenteApi {

    @GetMapping(value = APP_ROOT + "/ventes/{idVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par ID", notes = "Permet de chercher une vente par son ID",
            response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune vente avec cet ID n'a été trouvé dans la BDD")
    })
    VenteDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(value = APP_ROOT + "/ventes/{codeVente}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une vente par CODE", notes = "Permet de chercher une vente par son CODE",
            response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune vente avec ce CODE n'a été trouvé dans la BDD")
    })
    VenteDto findByCode(@PathVariable("codeVente") String code);

    @PostMapping(value = APP_ROOT + "/ventes/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une vente", notes = "Permet d'ajouter et/ou de modifier un vente",
            response = VenteDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet vente a été créé/modifié"),
            @ApiResponse(code = 400, message = "L'objet vente est invalide")
    })
    VenteDto save(@RequestBody VenteDto venteDto);

    @GetMapping(value = APP_ROOT + "/ventes/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des ventes", notes = "Permet de chercher et renvoyer la liste des ventes " +
            "qui existent dans la BDD", responseContainer = "List<VenteDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des ventes / liste vide")
    })
    List<VenteDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/ventes/delete/{idVente}")
    @ApiOperation(value = "Supprimer une vente", notes = "Permet de supprimer une vente dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La vente a été supprimé")
    })
    void delete(@PathVariable("idVente") Integer id);

}
