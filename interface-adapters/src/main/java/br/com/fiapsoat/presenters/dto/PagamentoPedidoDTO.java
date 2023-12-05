package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.pagamento.PagamentoPedido;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PagamentoPedidoDTO {

    @Schema(description = "Número retornado ao efetuar pedido", example = "1")
    private Long numeroDoPedido;

    public PagamentoPedido toPagamentoPedido() {
        return new PagamentoPedido(this.getNumeroDoPedido());
    }
}
