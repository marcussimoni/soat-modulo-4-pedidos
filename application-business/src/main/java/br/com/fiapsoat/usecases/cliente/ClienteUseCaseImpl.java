package br.com.fiapsoat.usecases.cliente;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.exceptions.ClientNotFoundException;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.usecases.inputports.cliente.ClienteInputPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteInputPort clienteInputPort;

    @Override
    @Transactional
    public Cliente salvar(Cliente cliente) {

        try {
            return clienteInputPort.findByCpf(cliente.getCpf());
        } catch (ClientNotFoundException e) {
            cliente.setId(null);
            return clienteInputPort.save(cliente);
        }

    }

    @Override
    public List<Cliente> listar() {
        return clienteInputPort.findAll();
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {

        Cpf cpfValueObject = new Cpf(cpf);
        return clienteInputPort.findByCpf(cpfValueObject);

    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return clienteInputPort.findById(id);
    }
}
