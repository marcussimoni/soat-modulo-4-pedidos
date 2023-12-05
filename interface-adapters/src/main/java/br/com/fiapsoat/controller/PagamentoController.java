package br.com.fiapsoat.controller;

import br.com.fiapsoat.presenters.dto.*;

public interface PagamentoController {

    PagamentoConfirmadoDTO confirmacaoPagamento(ConfirmacaoPagamentoDTO confirmacaoPagamentoDTO);

    PagamentoDTO consultarStatusDoPagamento(Long idPagamento);
    ComprovanteDTO buscarComprovante(Long idPagamento);

    ComprovanteDTO pagamento(PagamentoPedidoDTO pagamentoPedidoDTO);
}
