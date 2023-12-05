package br.com.fiapsoat.entities.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;

public class ClientNotFoundException extends AbstractException {
    public ClientNotFoundException(String erro, List<String> detalhes, HttpStatus statusCode) {
        super(erro, detalhes, statusCode);
    }
}
