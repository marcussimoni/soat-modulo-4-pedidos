package br.com.fiapsoat.presenters.pagamento;

import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.recibo.Comprovante;
import br.com.fiapsoat.presenters.dto.ComprovanteDTO;
import br.com.fiapsoat.presenters.dto.PagamentoDTO;

public interface PagamentoPresenter {
    PagamentoDTO toPagamentoDTO(Pagamento pagamento);

    ComprovanteDTO toComprovanteDTO(Comprovante comprovante);
}
