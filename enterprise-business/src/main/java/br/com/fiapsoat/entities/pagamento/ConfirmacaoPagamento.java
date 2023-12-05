package br.com.fiapsoat.entities.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import lombok.Data;

@Data
public class ConfirmacaoPagamento {

    private Long numeroDoPedido;
    private StatusDoPagamento statusDoPagamento;

}
