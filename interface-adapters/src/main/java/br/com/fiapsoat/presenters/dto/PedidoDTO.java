package br.com.fiapsoat.presenters.dto;

import br.com.fiapsoat.entities.enums.EtapaDoPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoDTO {

    @Schema(description = "Número gerado para identificar o pedido", example = "1")
    private Long numeroDoPedido;

    @Schema(description = "Nome do cliente caso identificado. Caso contrário apresenta 'Não identificado'", example = "João")
    private String cliente;

    @Schema(description = "Etapa em que o pedido se encontra", example = "Em preparação")
    private EtapaDoPedido etapaDaPreparacao;

    @Schema(description = "Status do pagamento do pedido", example = "Pago")
    private String statusDoPagamento;

    @Schema(description = "Data e hora em que o pedido foi realizado", example = "2023-01-01T00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pedidoRealizadoEm;

    @Schema(description = "Data e hora em que o pedido foi retirado pelo cliente", example = "2023-01-01T00:00:00")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime pedidoRetiradoEm;

    @Schema(description = "Tempo decorrido desde a confirmação de pagamento e recebimento do pedido pela equipe de preparo", example = "5 Minutos")
    private String tempoDesdeRecebimentoDoPedido;

    @Schema(description = "Soma do preço de todos os itens do pedido", example = "R$ 23.50")
    private String valorTotalDoPedido;

    @Schema(description = "Todos os produtos selecionados pelo cliente durante o pedido", example = "")
    private List<ProdutoDTO> items;


}
