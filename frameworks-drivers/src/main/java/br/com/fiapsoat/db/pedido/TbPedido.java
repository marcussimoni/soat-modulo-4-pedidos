package br.com.fiapsoat.db.pedido;

import br.com.fiapsoat.db.cliente.TbCliente;
import br.com.fiapsoat.db.produto.TbProduto;
import br.com.fiapsoat.entities.enums.EtapaDoPedido;
import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import static br.com.fiapsoat.entities.enums.StatusDoPagamento.AGUARDANDO_PAGAMENTO;

@Data
@Entity
@NoArgsConstructor
@Table(name = "pedido")
public class TbPedido implements Serializable {

    public TbPedido(TbCliente cliente, List<TbProduto> produtos) {
        setPedidoRealizadoEm(LocalDateTime.now());
        setEtapa(EtapaDoPedido.RECEBIDO);
        setStatusDoPagamento(AGUARDANDO_PAGAMENTO);
        setProdutos(produtos);
        setCliente(cliente);
    }

    public TbPedido(Pedido pedido){
        this.id = pedido.getId();
        if(pedido.getCliente() != null){
            this.cliente = new TbCliente(pedido.getCliente());
        }
        this.etapa = pedido.getEtapa();
        this.statusDoPagamento = pedido.getStatusDoPagamento();
        this.pedidoRealizadoEm = pedido.getPedidoRealizadoEm();
        this.pedidoRetirado = pedido.getPedidoRetirado();
        this.produtos = pedido.getProdutos().stream().map(TbProduto::new).toList();
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private TbCliente cliente;

    @Enumerated(EnumType.STRING)
    private EtapaDoPedido etapa;

    @Column(name = "status_pagamento")
    @Enumerated(EnumType.STRING)
    private StatusDoPagamento statusDoPagamento;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pedido_realizado_em")
    private LocalDateTime pedidoRealizadoEm;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "pedido_retirado_em")
    private LocalDateTime pedidoRetirado;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id"))
    private List<TbProduto> produtos;

    @PreUpdate
    private void atualizarCampoPedidoRetirado(){
        if(etapa == EtapaDoPedido.FINALIZADO && pedidoRetirado == null){
            this.pedidoRetirado = LocalDateTime.now();
        }
    }

    public Pedido pedidoBuilder(){
        Pedido pedido = new Pedido();
        pedido.setId(this.getId());
        if(this.getCliente() != null){
            pedido.setCliente(this.getCliente().clienteBuilder());
        }
        pedido.setPedidoRealizadoEm(this.getPedidoRealizadoEm());
        pedido.setPedidoRetirado(this.getPedidoRetirado());
        pedido.setEtapa(this.getEtapa());
        pedido.setProdutos(this.getProdutos().stream().map(TbProduto::produtoBuilder).toList());
        pedido.setStatusDoPagamento(this.getStatusDoPagamento());
        return pedido;
    }

}
