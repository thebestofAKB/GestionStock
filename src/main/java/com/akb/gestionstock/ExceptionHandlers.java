package com.akb.gestionstock;

import com.akb.gestionstock.exception.ErrorCodes;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionHandlers {

    private Integer httpCode;

    private ErrorCodes errorCode;

    private String message;

    private List<String> errors = new ArrayList<>();
}
