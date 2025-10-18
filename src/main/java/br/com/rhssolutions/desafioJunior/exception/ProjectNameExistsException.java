package br.com.rhssolutions.desafioJunior.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ProjectNameExistsException extends RuntimeException {
    public ProjectNameExistsException(String message) {
        super(message);
    }
}
