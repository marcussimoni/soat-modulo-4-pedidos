package br.com.fiapsoat.entities.valueobjects.nome;

import br.com.fiapsoat.entities.exceptions.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;

public class Nome {

    private final String value;

    public Nome(String nome){
        this.value = nome;
        validate(this.value);
    }

    private void validate(String value) {

        if(StringUtils.isBlank(value)){
            throw new BusinessException("Nome do cliente inválido", List.of(), HttpStatus.BAD_REQUEST);
        }

    }

    public String getValue(){
        return this.value;
    }

}
