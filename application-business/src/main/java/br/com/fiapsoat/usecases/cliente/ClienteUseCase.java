package br.com.fiapsoat.usecases.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;

import java.util.List;

public interface ClienteUseCase {

    Cliente salvar(Cliente cliente);
    List<Cliente> listar();

    Cliente buscarClientePorCpf(String cpf);

    Cliente buscarClientePorId(Long id);
}
