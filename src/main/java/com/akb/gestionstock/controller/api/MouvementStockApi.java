package com.akb.gestionstock.controller.api;

import com.akb.gestionstock.dto.ClientDto;
import com.akb.gestionstock.dto.MouvementStockDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.akb.gestionstock.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/mouvementStocks")
public interface MouvementStockApi {

    @GetMapping(value = APP_ROOT + "/mouvementStocks/{idMvtStock}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Mouvement de Stock par ID", notes = "Permet de chercher un Mouvement de Stock par son ID",
            response = MouvementStockDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet Mouvement de Stock a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun Mouvement de Stock avec cet ID n'a été trouvé dans la BDD")
    })
    MouvementStockDto findById(@PathVariable("idMvtStock") Integer id);

    @PostMapping(value = APP_ROOT + "/mouvementStocks/create", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un Mouvement de Stock", notes = "Permet d'ajouter et/ou de modifier un Mouvement de Stock",
            response = MouvementStockDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet Mouvement de Stock a été créé/modifié"),
            @ApiResponse(code = 400, message = "L'objet Mouvement de Stock est invalide")
    })
    MouvementStockDto save(@RequestBody MouvementStockDto mouvementStockDto);

    @GetMapping(value = APP_ROOT + "/mouvementStocks/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher la liste des Mouvements de Stocks", notes = "Permet de chercher et renvoyer la liste des Mouvements de Stocks " +
            "qui existent dans la BDD", responseContainer = "List<MouvementStockDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Mouvements de Stocks / liste vide")
    })
    List<MouvementStockDto> findAll();

    @DeleteMapping(value = APP_ROOT + "/mouvementStocks/delete/{idMvtStock}")
    @ApiOperation(value = "Supprimer un Mouvement de Stock", notes = "Permet de supprimer un Mouvement de Stock dans la BDD")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le Mouvement de Stock a été supprimé")
    })
    void delete(@PathVariable("idMvtStock") Integer id);

}
