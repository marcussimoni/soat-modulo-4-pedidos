package br.com.fiapsoat.entities.pedido;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.enums.EtapaDoPedido;
import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.produto.Produto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiapsoat.entities.enums.StatusDoPagamento.AGUARDANDO_PAGAMENTO;

@Data
@NoArgsConstructor
public class Pedido implements Serializable {

    public Pedido(Cliente cliente, List<Produto> produtos) {
        setPedidoRealizadoEm(LocalDateTime.now());
        setEtapa(EtapaDoPedido.RECEBIDO);
        setStatusDoPagamento(AGUARDANDO_PAGAMENTO);
        setProdutos(produtos);
        setCliente(cliente);
    }
    private Long id;
    private Cliente cliente;
    private EtapaDoPedido etapa;
    private StatusDoPagamento statusDoPagamento;
    private LocalDateTime pedidoRealizadoEm;
    private LocalDateTime pedidoRetirado;
    private List<Produto> produtos;

}
