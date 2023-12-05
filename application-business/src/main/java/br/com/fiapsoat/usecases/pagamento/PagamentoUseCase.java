package br.com.fiapsoat.usecases.pagamento;

import br.com.fiapsoat.entities.pagamento.ConfirmacaoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.pagamento.PagamentoPedido;
import br.com.fiapsoat.entities.recibo.Comprovante;

public interface PagamentoUseCase {
    Comprovante pagamento(PagamentoPedido pagamentoPedido);
    void confirmacaoPagamento(ConfirmacaoPagamento confirmacaoPagamento);
    Comprovante buscarComprovante(Long numeroDoPedido);

    Pagamento buscarPagamentoPorNumeroDoPedido(Long numeroDoPedido);
}
