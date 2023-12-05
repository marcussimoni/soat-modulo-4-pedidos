package br.com.fiapsoat.presenters.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PagamentoDTO {

    private Long id;
    private Long numeroDoPedido;
    private LocalDateTime dataDoPagamento;
    private LocalDateTime dataDeConfirmacao;
    private String status;

}
