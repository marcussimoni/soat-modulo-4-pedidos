package br.com.fiapsoat.entities.valueobjects.cpf;

import br.com.fiapsoat.entities.exceptions.BusinessException;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
public class Cpf {

    private String value;

    public Cpf(String cpf){
        this.value = cpf;
        validate(cpf);
    }

    private void validate(String cpf){
        String value = cpf.replaceAll("\\D", "");
        if(value.length() < 11){
            throw new BusinessException("Cpf inválido", List.of(), HttpStatus.BAD_REQUEST);
        }
        this.value = value;
    }

    public String format(){
        return getValue().replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
