package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PagamentoConfirmadoDTO {

    private String mensagem;
    private LocalDateTime dataDeProcessamento;

    public PagamentoConfirmadoDTO(StatusDoPagamento statusDoPagamento){
        this.mensagem = statusDoPagamento.getStatus();
        this.dataDeProcessamento = LocalDateTime.now();
    }

}
