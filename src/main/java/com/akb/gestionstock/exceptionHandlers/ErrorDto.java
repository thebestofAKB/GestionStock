package com.akb.gestionstock.exceptionHandlers;

import com.akb.gestionstock.exception.ErrorCodes;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDto {

    private Integer httpCode;

    private ErrorCodes errorCode;

    private String message;

    private List<String> errors = new ArrayList<>();
}
