package br.com.fiapsoat.gateways.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;

public interface PagamentoGateway {
    Pagamento registrarPagamento(Pagamento pagamento);

    Pagamento buscarPagamentoPorId(Long idPagamento);

    void atualizarStatusPagamento(Pagamento pagamento, StatusDoPagamento statusDoPagamento);

    Pagamento buscarPagamentoPorNumeroDoPedido(Long numeroDoPedido);
}
