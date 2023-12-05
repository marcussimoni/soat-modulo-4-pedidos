package br.com.fiapsoat.presenters.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.presenters.dto.ClienteDTO;

import java.util.List;

public interface ClientePresenter {

    List<ClienteDTO> toClienteDTO(List<Cliente> clientes);
    ClienteDTO toClienteDTO(Cliente clienteSalvo);

}
