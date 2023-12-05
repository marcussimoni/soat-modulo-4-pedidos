package br.com.fiapsoat.db.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.entities.exceptions.ClientNotFoundException;
import br.com.fiapsoat.gateways.cliente.ClienteGateway;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class ClienteGatewayImpl implements ClienteGateway {

    private final SpringRepository repository;

    @Override
    public Cliente findByCpf(Cpf cpf) {

        TbCliente cliente = repository.findByCpf(cpf.getValue()).orElseThrow(() -> {
            throw new ClientNotFoundException("Cliente não encontrado", List.of(MessageFormat.format("Cliente não encontrado para o cpf {0}", cpf.format())), HttpStatus.BAD_REQUEST);
        });

        return cliente.clienteBuilder();

    }

    @Override
    public Cliente save(Cliente cliente) {

        TbCliente tbCliente = new TbCliente(cliente);

        TbCliente clienteSalvo = repository.save(tbCliente);

        return clienteSalvo.clienteBuilder();

    }

    @Override
    public List<Cliente> findAll() {
        Iterator<TbCliente> clientes = repository.findAll().iterator();
        return IteratorUtils
                .toList(clientes)
                .stream()
                .map(TbCliente::clienteBuilder)
                .toList();
    }

    @Override
    public Cliente findById(Long id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ClientNotFoundException("Cliente não encontrado", List.of(MessageFormat.format("Cliente não encontrado para o id {0}", id)), HttpStatus.BAD_REQUEST);
        }).clienteBuilder();
    }

    @Repository
    private interface SpringRepository extends CrudRepository<TbCliente, Long> {
        Optional<TbCliente> findByCpf(String cpf);
    }

}
