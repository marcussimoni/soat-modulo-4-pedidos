package br.com.fiapsoat.usecases.pagamento;

import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.entities.pagamento.ConfirmacaoPagamento;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.pagamento.PagamentoPedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.recibo.Comprovante;
import br.com.fiapsoat.usecases.inputports.pagamento.PagamentoInputPort;
import br.com.fiapsoat.usecases.pedido.PedidoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.fiapsoat.entities.enums.StatusDoPagamento.AGUARDANDO_CONFIRMACAO;

@Service
@AllArgsConstructor
public class PagamentoUseCaseImpl implements PagamentoUseCase {

    private PedidoUseCase pedidoUseCase;
    private PagamentoInputPort pagamentoInputPort;

    @Override
    public Comprovante pagamento(PagamentoPedido pagamentoPedido) {

        Pagamento pagamento = new Pagamento(pedidoUseCase.buscarPedidoPorId(pagamentoPedido.getNumeroDoPedido()));

        Pagamento pagamentoRegistrado = pagamentoInputPort.registrarPagamento(pagamento);

        pedidoUseCase.atualizarStatusPagamentoDoPedido(pagamentoPedido.getNumeroDoPedido(), AGUARDANDO_CONFIRMACAO);

        return comprovanteBuilder(pagamentoRegistrado);

    }

    @Override
    public void confirmacaoPagamento(ConfirmacaoPagamento confirmacaoPagamento) {

        List<StatusDoPagamento> statusDePagamentoValido = List.of(StatusDoPagamento.CONFIRMADO, StatusDoPagamento.RECUSADO);

        StatusDoPagamento statusDoPagamento = confirmacaoPagamento.getStatusDoPagamento();

        if(statusDoPagamento == null || !statusDePagamentoValido.contains(statusDoPagamento)){
            String message = MessageFormat.format("Status {0} é inválido. Utilizar as opções 'CONFIRMADO' ou 'RECUSADO'.", statusDoPagamento);
            throw new BusinessException(message, List.of(), HttpStatus.BAD_REQUEST);
        }

        Pagamento pagamento = pagamentoInputPort.buscarPagamentoPorNumeroDoPedido(confirmacaoPagamento.getNumeroDoPedido());

        if(statusDePagamentoValido.contains(pagamento.getStatus())){
            String message = MessageFormat.format("Pagamento já realizado para o pedido {0}. Status do pagamento: {1} [{2}]", confirmacaoPagamento.getNumeroDoPedido(), pagamento.getStatus().getStatus(), pagamento.getStatus());
            throw new BusinessException(message, List.of(), HttpStatus.BAD_REQUEST);
        }

        pagamentoInputPort.atualizarStatusPagamento(pagamento, statusDoPagamento);

        pedidoUseCase.atualizarStatusPagamentoDoPedido(pagamento.getPedido().getId(), statusDoPagamento);

    }

    @Override
    public Comprovante buscarComprovante(Long numeroDoPedido) {
        return comprovanteBuilder(pagamentoInputPort.buscarPagamentoPorNumeroDoPedido(numeroDoPedido));
    }

    @Override
    public Pagamento buscarPagamentoPorNumeroDoPedido(Long numeroDoPedido) {
        return pagamentoInputPort.buscarPagamentoPorNumeroDoPedido(numeroDoPedido);
    }

    private String calcularValorTotalDoPedido(Pagamento pagamentoRegistrado) {
        Double total = pagamentoRegistrado.getPedido().getProdutos().stream().map(Produto::getPreco).reduce(0.0, Double::sum);
        return MessageFormat.format("R$ {0}", BigDecimal.valueOf(total).setScale(2));
    }

    private List<String> itensPedido(Pagamento pagamento) {
        return pagamento.getPedido().getProdutos().stream().map(produto -> {
            return MessageFormat.format("Item: {0} valor: R$ {1}", produto.getNome(), BigDecimal.valueOf(produto.getPreco()).setScale(2));
        }).collect(Collectors.toList());
    }

    private Comprovante comprovanteBuilder(Pagamento pagamentoRegistrado) {

        String totalDoPedido = calcularValorTotalDoPedido(pagamentoRegistrado);
        String nomeDoCliente = "Cliente não identificado";

        if(pagamentoRegistrado.getPedido().getCliente() != null) {
            nomeDoCliente = pagamentoRegistrado.getPedido().getCliente().getNome().getValue();
        }

        return Comprovante
                .builder()
                .idDoPagamento(pagamentoRegistrado.getId())
                .numeroDoPedido(pagamentoRegistrado.getPedido().getId())
                .dataHoraPagamento(pagamentoRegistrado.getDataDoPagamento())
                .codigoDeAutenticacao(pagamentoRegistrado.getCodigoDeAutenticacao())
                .codigoDoPedido(pagamentoRegistrado.getId())
                .itensPedido(itensPedido(pagamentoRegistrado))
                .valorTotal(totalDoPedido)
                .nomeDoCliente(nomeDoCliente)
                .build();
    }

}
