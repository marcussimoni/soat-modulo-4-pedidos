package br.com.fiapsoat.integracao.clientes;

import br.com.fiapsoat.entities.exceptions.ClientNotFoundException;
import br.com.fiapsoat.gateways.cliente.ClienteGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteGatewayTest {

    @Autowired
    private ClienteGateway clienteGateway;

    @Test
    @DisplayName("Deve validar id de cliente invalido")
    public void deveValidarIdDeClienteInvalido(){

        Assertions.assertThrows(ClientNotFoundException.class, () -> {
           clienteGateway.findById(9999L);
        });

    }

}
