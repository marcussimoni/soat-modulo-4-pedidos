package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.ConfirmacaoPagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ConfirmacaoPagamentoDTO {

    @Schema(description = "Número retornado ao efetuar pedido", example = "1")
    private Long numeroDoPedido;

    @Schema(description = "Status do pagamento retornado pela instituição financeira", example = "CONFIRMADO")
    private StatusDoPagamento statusDoPagamento;

    public ConfirmacaoPagamento toConfirmacaoPagamento() {
        ConfirmacaoPagamento c = new ConfirmacaoPagamento();
        c.setNumeroDoPedido(getNumeroDoPedido());
        c.setStatusDoPagamento(getStatusDoPagamento());
        return c;
    }
}
