package br.com.fiapsoat.inputports.pedidos;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.gateways.pedido.PedidoGateway;
import br.com.fiapsoat.usecases.inputports.pedido.PedidoInputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoInputPortImpl implements PedidoInputPort {

    private final PedidoGateway pedidoGateway;

    @Override
    public Pedido findById(Long id) {
        return pedidoGateway.findById(id);
    }

    @Override
    public Pedido save(Pedido pedido) {
        return pedidoGateway.save(pedido);
    }

    @Override
    public List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento) {
        return pedidoGateway.buscarPedidosDisponiveis(statusDoPagamento);
    }

    @Override
    public List<Pedido> buscarTodosOsPedidosDisponiveis() {
        return pedidoGateway.buscarTodosOsPedidos();
    }
}
