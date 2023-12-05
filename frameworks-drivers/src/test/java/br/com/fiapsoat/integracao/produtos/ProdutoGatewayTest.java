package br.com.fiapsoat.integracao.produtos;

import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.entities.exceptions.ClientNotFoundException;
import br.com.fiapsoat.gateways.produto.ProdutoGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ProdutoGatewayTest {

    @Autowired
    private ProdutoGateway produtoGateway;

    @Test
    @DisplayName("Deve validar id de produto invalido")
    public void deveValidarIdDeClienteInvalido(){

        Assertions.assertThrows(BusinessException.class, () -> {
           produtoGateway.findById(9999L);
        });

    }

}
