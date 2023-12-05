package br.com.fiapsoat.integracao.auth;

import br.com.fiapsoat.entities.exceptions.ClientNotFoundException;
import br.com.fiapsoat.gateways.cliente.ClienteGateway;
import br.com.fiapsoat.usecases.auth.AuthUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class AuthUseCaseTest {

    @Autowired
    private AuthUseCase authUseCase;

    @Test
    @DisplayName("Deve gerar token para cliente não identificado")
    public void deveGerarTokenParaClienteNaoIdentificado(){

        String token = authUseCase.generateToken("999.999.999-99");

        String subject = authUseCase.getSubject(token);

        Assertions.assertTrue(subject.isEmpty());

    }

}
