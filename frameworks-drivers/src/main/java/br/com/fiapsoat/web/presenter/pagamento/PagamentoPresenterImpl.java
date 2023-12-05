package br.com.fiapsoat.web.presenter.pagamento;

import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.recibo.Comprovante;
import br.com.fiapsoat.presenters.dto.ComprovanteDTO;
import br.com.fiapsoat.presenters.dto.PagamentoDTO;
import br.com.fiapsoat.presenters.pagamento.PagamentoPresenter;
import org.springframework.stereotype.Component;

@Component
public class PagamentoPresenterImpl implements PagamentoPresenter {
    @Override
    public PagamentoDTO toPagamentoDTO(Pagamento pagamento) {

        PagamentoDTO dto = new PagamentoDTO();

        dto.setId(pagamento.getId());
        dto.setNumeroDoPedido(pagamento.getPedido().getId());
        dto.setDataDoPagamento(pagamento.getDataDoPagamento());
        dto.setDataDeConfirmacao(pagamento.getDataDeConfirmacao());
        dto.setStatus(pagamento.getStatus().getStatus());

        return dto;

    }

    @Override
    public ComprovanteDTO toComprovanteDTO(Comprovante comprovante) {
        ComprovanteDTO dto = new ComprovanteDTO();
        dto.setCodigoDoPagamento(comprovante.getIdDoPagamento());
        dto.setNumeroDoPedido(comprovante.getNumeroDoPedido());
        dto.setDataHoraPagamento(comprovante.getDataHoraPagamento());
        dto.setItensPedido(comprovante.getItensPedido());
        dto.setValorTotal(comprovante.getValorTotal());
        dto.setNomeDoCliente(comprovante.getNomeDoCliente());
        return dto;
    }

}
