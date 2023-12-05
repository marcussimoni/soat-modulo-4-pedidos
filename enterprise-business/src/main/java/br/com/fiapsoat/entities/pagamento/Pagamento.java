package br.com.fiapsoat.entities.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Pagamento implements Serializable {

    public Pagamento(Pedido pedido){
        this();
        this.pedido = pedido;
    }
    public Pagamento(){
        codigoDeAutenticacao = UUID.randomUUID().toString();
        dataDoPagamento = LocalDateTime.now();
        status = StatusDoPagamento.AGUARDANDO_CONFIRMACAO;
    }

    private Long id;
    private Pedido pedido;
    private String codigoDeAutenticacao;
    private LocalDateTime dataDoPagamento;
    private LocalDateTime dataDeConfirmacao;
    private StatusDoPagamento status;

}
