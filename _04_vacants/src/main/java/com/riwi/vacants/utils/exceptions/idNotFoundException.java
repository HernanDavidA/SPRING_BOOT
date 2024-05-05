package com.riwi.vacants.utils.exceptions;
                                        // Runtime exception clase general de errores en Java, implementa constructores en esta clase y genera el error personalizado
public class idNotFoundException extends RuntimeException{

    private static final String ERROR_MESSAGE = "No register on the entity %s ";

    public idNotFoundException(String nameEntity){
        super(String.format(ERROR_MESSAGE, nameEntity));
    }
}
