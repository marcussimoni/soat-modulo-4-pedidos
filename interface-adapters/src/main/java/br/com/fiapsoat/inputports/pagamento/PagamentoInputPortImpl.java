package br.com.fiapsoat.inputports.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.gateways.pagamento.PagamentoGateway;
import br.com.fiapsoat.usecases.inputports.pagamento.PagamentoInputPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PagamentoInputPortImpl implements PagamentoInputPort {

    private final PagamentoGateway pagamentoGateway;

    @Override
    public Pagamento registrarPagamento(Pagamento pagamento) {
        return pagamentoGateway.registrarPagamento(pagamento);
    }

    @Override
    public Pagamento buscarPagamentoPorId(Long idPagamento) {
        return pagamentoGateway.buscarPagamentoPorId(idPagamento);
    }

    @Override
    public void atualizarStatusPagamento(Pagamento pagamento, StatusDoPagamento statusDoPagamento) {
        pagamentoGateway.atualizarStatusPagamento(pagamento, statusDoPagamento);
    }

    @Override
    public Pagamento buscarPagamentoPorNumeroDoPedido(Long numeroDoPedido) {
        return pagamentoGateway.buscarPagamentoPorNumeroDoPedido(numeroDoPedido);
    }

}
