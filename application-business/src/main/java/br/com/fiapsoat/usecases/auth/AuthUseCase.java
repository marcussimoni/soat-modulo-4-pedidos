package br.com.fiapsoat.usecases.auth;

import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;

public interface AuthUseCase {

    Boolean isTokenExpired(String token);

    String getSubject(String token);

    String generateToken(String cpf);

}
