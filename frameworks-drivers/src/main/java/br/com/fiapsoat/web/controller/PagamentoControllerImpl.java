package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.controller.PagamentoController;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.recibo.Comprovante;
import br.com.fiapsoat.presenters.dto.*;
import br.com.fiapsoat.presenters.pagamento.PagamentoPresenter;
import br.com.fiapsoat.usecases.pagamento.PagamentoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("pagamento")
@Tag(name = "Pagamentos", description = "Gerencia os pagamentos para os pedidos realizados")
public class PagamentoControllerImpl implements PagamentoController {

    private final PagamentoUseCase pagamentoUserCase;
    private final PagamentoPresenter pagamentoPresenter;

    @Override
    @PutMapping(path = "/webhook")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Pagamentos", summary = "Webhook", description = "Webhook para confirmação utilizado pelo gateway de pagamento. Utilizar as opções 'CONFIRMADO' ou 'RECUSADO'")
    public PagamentoConfirmadoDTO confirmacaoPagamento(
            @RequestBody ConfirmacaoPagamentoDTO confirmacaoPagamentoDTO
    ) {
        pagamentoUserCase.confirmacaoPagamento(confirmacaoPagamentoDTO.toConfirmacaoPagamento());
        return new PagamentoConfirmadoDTO(confirmacaoPagamentoDTO.getStatusDoPagamento());
    }

    @Override
    @GetMapping(path = "/{numero-do-pedido}")
    @Operation(tags = "Pagamentos", summary = "Consultar status do pagamento", description = "Consulta status do pagamento para os pedidos realizados")
    public PagamentoDTO consultarStatusDoPagamento(@Parameter(name = "numero-do-pedido", description = "Número do pedido") @PathVariable("numero-do-pedido") Long numeroDoPedido) {
        Pagamento pagamento = pagamentoUserCase.buscarPagamentoPorNumeroDoPedido(numeroDoPedido);
        return pagamentoPresenter.toPagamentoDTO(pagamento);
    }

    @Override
    @GetMapping(path = "/{numero-do-pedido}/comprovante")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Pagamentos", summary = "Comprovante", description = "Comprovante da confirmação de pagamento do pedido")
    public ComprovanteDTO buscarComprovante(@Parameter(description = "Número do pedido", name = "numero-do-pedido") @PathVariable("numero-do-pedido") Long numeroDoPedido) {
        return pagamentoPresenter.toComprovanteDTO(pagamentoUserCase.buscarComprovante(numeroDoPedido));
    }

    @Override
    @PostMapping
    @Operation(tags = "Pagamentos", summary = "Pagamento do pedido", description = "Realiza o pagamento do pedido para que o pedido possa ser encaminhado para o preparo. Caso o pagamento não seja concluído o pedido não poderá avançar para as próximas etapas.")
    public ComprovanteDTO pagamento(
            @RequestBody PagamentoPedidoDTO pagamentoPedidoDTO
            ){
        Comprovante comprovante = pagamentoUserCase.pagamento(pagamentoPedidoDTO.toPagamentoPedido());
        return pagamentoPresenter.toComprovanteDTO(comprovante);
    }
}
