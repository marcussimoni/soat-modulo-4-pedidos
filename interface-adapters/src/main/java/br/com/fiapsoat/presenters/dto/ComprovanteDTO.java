package br.com.fiapsoat.presenters.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ComprovanteDTO {

    private String nomeDoCliente;
    private long codigoDoPagamento;
    private long numeroDoPedido;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraPagamento;

    private List<String> itensPedido;
    private String valorTotal;

}
