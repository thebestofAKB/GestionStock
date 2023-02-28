package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.ArticleDto;
import com.akb.gestionstock.dto.EntrepriseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/entreprises")
public interface EntrepriseApi {

    @GetMapping(value = APP_ROOT + "/entreprises/{idEntreprise}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Entreprise par son ID", notes = "Permet de chercher une entreprise par son ID",
            response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet Entreprise a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun Entreprise avec cet ID n'a été trouvé dans la BDD")
    })
    EntrepriseDto findById(@PathVariable("idEntreprise") Integer id);

    @PostMapping(value = APP_ROOT + "/entreprises/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer une entreprise", notes = "Permet d'ajouter et/ou de modifier une entreprise",
            response = EntrepriseDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet entreprise a été créé/modifié"),
            @ApiResponse(code = 400, message = "L'objet entreprise est invalide")
    })
    EntrepriseDto save(EntrepriseDto entrepriseDto);

    @GetMapping(value = APP_ROOT + "/entreprises/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des entreprises", notes = "Permet de chercher et renvoyer la liste des entreprises " +
            "qui existent dans la BDD", responseContainer = "List<EntrepriseDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des entreprises / liste vide")
    })
    List<EntrepriseDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/entreprises/delete/{idEntreprise}")
    @ApiOperation(value = "Supprimer une entreprise", notes = "Permet de supprimer une entreprise dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'entreprise a été supprimé")
    })
    void delete(@PathVariable("idEntreprise") Integer id);

}
