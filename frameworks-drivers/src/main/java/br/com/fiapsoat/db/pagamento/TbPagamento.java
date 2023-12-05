package br.com.fiapsoat.db.pagamento;

import br.com.fiapsoat.db.pedido.TbPedido;
import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "pagamento")
public class TbPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private TbPedido pedido;

    @Column(name = "codigo_de_autenticacao")
    private String codigoDeAutenticacao;

    @Column(name = "data_do_pagamento")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataDoPagamento;

    @Column(name = "data_de_confirmacao")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataDeConfirmacao;

    @Enumerated(EnumType.STRING)
    private StatusDoPagamento status;

    public TbPagamento(Pagamento pagamento){
        this.id = pagamento.getId();
        this.pedido = new TbPedido(pagamento.getPedido());
        this.codigoDeAutenticacao = pagamento.getCodigoDeAutenticacao();
        this.dataDoPagamento = pagamento.getDataDoPagamento();
        this.dataDeConfirmacao = pagamento.getDataDeConfirmacao();
        this.status = pagamento.getStatus();
    }

    public Pagamento pagamentoBuilder() {
        Pagamento pagamento = new Pagamento();
        pagamento.setDataDoPagamento(this.getDataDoPagamento());
        pagamento.setDataDeConfirmacao(this.getDataDeConfirmacao());
        pagamento.setId(this.getId());
        pagamento.setPedido(this.getPedido().pedidoBuilder());
        pagamento.setStatus(this.getStatus());
        pagamento.setCodigoDeAutenticacao(this.getCodigoDeAutenticacao());
        return pagamento;
    }
}
