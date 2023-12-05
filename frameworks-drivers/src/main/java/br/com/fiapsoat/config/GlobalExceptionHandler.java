package br.com.fiapsoat.config;

import br.com.fiapsoat.entities.exceptions.BusinessError;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.entities.exceptions.ClientNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<BusinessError> businessException(BusinessException exception, WebRequest request) {
        BusinessError error = BusinessError
                .builder()
                .erro(exception.getErro())
                .detalhes(exception.getDetalhes())
                .timestamp(LocalDateTime.now())
                .build();
        logger.error(error.getErro(), exception);
        return ResponseEntity.status(exception.getStatusCode()).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ClientNotFoundException.class)
    public ResponseEntity<BusinessError> clientNotFoundException(ClientNotFoundException exception, WebRequest request) {
        BusinessError error = BusinessError
                .builder()
                .erro(exception.getErro())
                .detalhes(exception.getDetalhes())
                .timestamp(LocalDateTime.now())
                .build();
        logger.error(error.getErro(), exception);
        return ResponseEntity.status(exception.getStatusCode()).body(error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<BusinessError> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.toList());
        BusinessError error = BusinessError
                .builder()
                .erro("Não foi possível concluir a operação.")
                .detalhes(errorMessages)
                .timestamp(LocalDateTime.now())
                .build();
        logger.error(error.getErro(), exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {Exception.class, RuntimeException.class})
    public ResponseEntity<BusinessError> exception(Exception exception, WebRequest request) {
        BusinessError error = BusinessError
                .builder()
                .erro("Não foi possível processar a requisição")
                .detalhes(List.of(exception.getMessage()))
                .timestamp(LocalDateTime.now())
                .build();
        logger.error(error.getErro(), exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
