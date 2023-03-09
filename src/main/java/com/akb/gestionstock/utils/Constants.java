package com.akb.gestionstock.utils;

public interface Constants {

    String APP_ROOT = "gestionstock/v1";

    String FOURNISSEUR_ENDPOINT = APP_ROOT + "/fournisseurs";
    String CREATE_FOURNISSEUR_ENDPOINT = FOURNISSEUR_ENDPOINT + "/create";
    String FIND_FOURNISSEUR_BY_ID_ENDPOINT = FOURNISSEUR_ENDPOINT + "/{idFournisseur}";
    String FIND_ALL_FOURNISSEUR_ENDPOINT = FOURNISSEUR_ENDPOINT + "/all";
    String DELETE_FOURNISSEUR_ENDPOINT = FOURNISSEUR_ENDPOINT + "/delete/{idFournisseur}";
}
