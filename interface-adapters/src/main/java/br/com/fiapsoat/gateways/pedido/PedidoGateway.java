package br.com.fiapsoat.gateways.pedido;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;

import java.util.List;

public interface PedidoGateway {
    List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento);

    List<Pedido> buscarTodosOsPedidos();

    Pedido save(Pedido pedido);

    Pedido findById(Long pedido);
}
