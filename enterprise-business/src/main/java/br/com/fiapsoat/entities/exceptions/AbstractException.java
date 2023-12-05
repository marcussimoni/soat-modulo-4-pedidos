package br.com.fiapsoat.entities.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
class AbstractException extends RuntimeException {
    protected String erro;
    protected List<String> detalhes;
    protected HttpStatus statusCode;
}
