package br.com.fiapsoat.presenters.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class CheckoutPedidoDTO {

    @Schema(description = "Lista de produtos selecionados para o pedido. Deve conter ao menos um produto para prosseguir")
    private List<ProdutoDTO> produtos;

}
