package br.com.fiapsoat.entities.valueobjects.email;

import br.com.fiapsoat.entities.exceptions.BusinessException;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.regex.Pattern;

@Getter
public class Email {

    private final String value;

    public Email(String value){
        this.value = value;
        validate(value);
    }

    private void validate(String email){

        Pattern pattern = Pattern.compile("^[a-zA-Z0-9._]+@[a-zA-Z]+\\.[a-zA-Z]");

        if(StringUtils.isBlank(email) || !pattern.matcher(email).find()){
            throw new BusinessException("E-mail inválido", List.of(), HttpStatus.BAD_REQUEST);
        }

    }

}
