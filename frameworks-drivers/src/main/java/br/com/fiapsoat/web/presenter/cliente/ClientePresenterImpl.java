package br.com.fiapsoat.web.presenter.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.presenters.cliente.ClientePresenter;
import br.com.fiapsoat.presenters.dto.ClienteDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientePresenterImpl implements ClientePresenter {

    @Override
    public List<ClienteDTO> toClienteDTO(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toClienteDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO toClienteDTO(Cliente clienteSalvo) {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(clienteSalvo.getId());
        clienteDTO.setNome(clienteSalvo.getNome().getValue());
        clienteDTO.setCpf(clienteSalvo.getCpf().format());
        clienteDTO.setEmail(clienteSalvo.getEmail().getValue());

        return clienteDTO;

    }
}
