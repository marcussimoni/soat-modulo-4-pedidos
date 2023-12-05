package br.com.fiapsoat.entities.recibo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class Comprovante {
    private long idDoPagamento;
    private long numeroDoPedido;
    private String codigoDeAutenticacao;
    private Long codigoDoPedido;
    private LocalDateTime dataHoraPagamento;
    private List<String> itensPedido;
    private String valorTotal;
    private String nomeDoCliente;
}
