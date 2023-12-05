package br.com.fiapsoat.usecases.inputports.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;

import java.util.List;

public interface ClienteInputPort {
    Cliente findByCpf(Cpf cpf);

    Cliente save(Cliente cliente);

    List<Cliente> findAll();

    Cliente findById(Long id);
}
