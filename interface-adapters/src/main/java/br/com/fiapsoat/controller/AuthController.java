package br.com.fiapsoat.controller;

import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.presenters.dto.CpfDTO;

public interface AuthController {

    String generateToken(CpfDTO cpf);

    Boolean validate(String token);

}
