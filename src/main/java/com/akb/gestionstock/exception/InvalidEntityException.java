package com.akb.gestionstock.exception;

import lombok.Getter;

import java.util.List;

/**
 * Cette classe est appelée quand on essaye d'envoyer
 * des données invalides en base de données
 */
public class InvalidEntityException extends RuntimeException {

    @Getter
    private ErrorCodes errorCode;

    @Getter
    private List<String> errors;

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEntityException(String message, Throwable cause, ErrorCodes errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public InvalidEntityException(String message, ErrorCodes errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public InvalidEntityException(String message, ErrorCodes errorCode,
                                  List<String> errors) {
        super(message);
        this.errorCode = errorCode;
        this.errors = errors;
    }
}
