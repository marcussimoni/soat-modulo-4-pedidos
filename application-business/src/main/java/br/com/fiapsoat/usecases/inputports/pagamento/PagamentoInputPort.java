package br.com.fiapsoat.usecases.inputports.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;

public interface PagamentoInputPort {
    Pagamento registrarPagamento(Pagamento pagamento);

    Pagamento buscarPagamentoPorId(Long idPagamento);

    void atualizarStatusPagamento(Pagamento pagamento, StatusDoPagamento statusDoPagamento);

    Pagamento buscarPagamentoPorNumeroDoPedido(Long numeroDoPedido);
}
