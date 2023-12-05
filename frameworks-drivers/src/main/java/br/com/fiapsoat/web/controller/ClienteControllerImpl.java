package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.controller.ClienteController;
import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.entities.valueobjects.email.Email;
import br.com.fiapsoat.entities.valueobjects.nome.Nome;
import br.com.fiapsoat.presenters.cliente.ClientePresenter;
import br.com.fiapsoat.presenters.dto.ClienteDTO;
import br.com.fiapsoat.usecases.cliente.ClienteUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("cliente")
@Tag(name = "Área administrativa - Clientes", description = "Gerencia os dados do cliente cadastrados para campanhas promocionais")
public class ClienteControllerImpl implements ClienteController {

    private final ClienteUseCase clienteUseCase;
    private final ClientePresenter clientePresenter;

    @Override
    @GetMapping
    @Operation(tags = "Área administrativa - Clientes", summary = "Listar clientes", description = "Lista todos os clientes cadastrados")
    public List<ClienteDTO> listar(){
        return clientePresenter.toClienteDTO(clienteUseCase.listar());
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "Área administrativa - Clientes", summary = "Cadastrar cliente", description = "Salva um novo cliente")
    public ClienteDTO salvar(@RequestBody ClienteDTO dto) {
        Cliente cliente = Cliente.builder()
                .id(dto.getId())
                .cpf(new Cpf(dto.getCpf()))
                .email(new Email(dto.getEmail()))
                .nome(new Nome(dto.getNome()))
                .build();

        Cliente clienteSalvo = clienteUseCase.salvar(cliente);

        return clientePresenter.toClienteDTO(clienteSalvo);
    }

}
