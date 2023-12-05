package br.com.fiapsoat.usecases.pedido;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;

import java.util.List;

public interface PedidoUseCase {
    Pedido buscarPedidoPorId(Long id);

    Pedido checkoutPedido(List<Produto> produtos);

    void atualizarStatusPagamentoDoPedido(Long pedido, StatusDoPagamento status);

    List<Pedido> listar(StatusDoPagamento statusDoPagamento);

    Pedido atualizaParaAProximaEtapaDoPedido(Long pedido);
}
