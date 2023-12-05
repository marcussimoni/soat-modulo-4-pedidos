package br.com.fiapsoat.inputports.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.gateways.cliente.ClienteGateway;
import br.com.fiapsoat.usecases.inputports.cliente.ClienteInputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteInputPortImpl implements ClienteInputPort {

    private final ClienteGateway clienteGateway;

    @Override
    public Cliente findByCpf(Cpf cpf) {
        return clienteGateway.findByCpf(cpf);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteGateway.save(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        return clienteGateway.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return clienteGateway.findById(id);
    }
}
