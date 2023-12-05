package br.com.fiapsoat.entities.exceptions;

import org.springframework.http.HttpStatus;

import java.util.List;


public class BusinessException extends AbstractException {

    public BusinessException(String erro, List<String> detalhes, HttpStatus statusCode) {
        super(erro, detalhes, statusCode);
    }
}
