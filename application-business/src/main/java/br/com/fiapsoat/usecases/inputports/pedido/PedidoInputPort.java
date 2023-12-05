package br.com.fiapsoat.usecases.inputports.pedido;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;

import java.util.List;

public interface PedidoInputPort {
    Pedido findById(Long id);

    Pedido save(Pedido pedido);

    List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento);
    List<Pedido> buscarTodosOsPedidosDisponiveis();
}
