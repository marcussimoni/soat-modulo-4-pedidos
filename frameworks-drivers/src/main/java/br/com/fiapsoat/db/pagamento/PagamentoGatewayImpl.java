package br.com.fiapsoat.db.pagamento;


import br.com.fiapsoat.db.pedido.TbPedido;
import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.entities.pagamento.Pagamento;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.gateways.pagamento.PagamentoGateway;
import br.com.fiapsoat.gateways.pedido.PedidoGateway;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import static br.com.fiapsoat.entities.enums.StatusDoPagamento.AGUARDANDO_CONFIRMACAO;

@Repository
@AllArgsConstructor
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final SpringRepository repository;
    private final PedidoGateway pedidoGateway;

    @Override
    @Transactional
    public Pagamento registrarPagamento(Pagamento pagamento) {

        TbPedido pedido = new TbPedido(pagamento.getPedido());

        Optional<TbPagamento> optional = repository.findByPedido(pedido);

        if(optional.isPresent()){

            TbPagamento tbPagamento = optional.get();
            String dataHoraPagamento = DateTimeFormatter.ofPattern("dd/MM/yyyy as HH:mm:ss").format(tbPagamento.getDataDoPagamento());
            String msg = MessageFormat.format("Pagamento realizado em {0} para o pedido de código {1}", dataHoraPagamento, tbPagamento.getPedido().getId());
            throw new BusinessException(msg, List.of(), HttpStatus.BAD_REQUEST);

        }

        TbPagamento tbPagamento = new TbPagamento(pagamento);

        tbPagamento.setStatus(AGUARDANDO_CONFIRMACAO);
        tbPagamento.getPedido().setStatusDoPagamento(AGUARDANDO_CONFIRMACAO);

        TbPagamento pagamentoRegistrado = repository.save(tbPagamento);

        return pagamentoRegistrado.pagamentoBuilder();
    }

    @Override
    public Pagamento buscarPagamentoPorId(Long idPagamento) {
        Optional<TbPagamento> optional = repository.findById(idPagamento);

        if(optional.isEmpty()){
            String msg = MessageFormat.format("Pagamento não encontrado para o id {0}", idPagamento);
            throw new BusinessException(msg, List.of(), HttpStatus.BAD_REQUEST);
        }

        return optional.get().pagamentoBuilder();
    }

    @Override
    public void atualizarStatusPagamento(Pagamento pagamento, StatusDoPagamento statusDoPagamento) {

        Optional<TbPagamento> optional = repository.findById(pagamento.getId());

        if(optional.isPresent()){
            TbPagamento entity = optional.get();
            entity.setStatus(statusDoPagamento);
            entity.setDataDeConfirmacao(LocalDateTime.now());
            repository.save(entity);
        }

    }

    @Override
    public Pagamento buscarPagamentoPorNumeroDoPedido(Long numeroDoPedido) {

        Pedido pedido = pedidoGateway.findById(numeroDoPedido);

        Optional<TbPagamento> pagamento = repository.findByPedido(new TbPedido(pedido));
        if(pagamento.isEmpty()){
            String msg = MessageFormat.format("Pagamento não encontrado para o número de pedido {0}", numeroDoPedido);
            throw new BusinessException(msg, List.of(), HttpStatus.BAD_REQUEST);
        }
        return pagamento.get().pagamentoBuilder();
    }

    @Repository
    private interface SpringRepository extends CrudRepository<TbPagamento, Long> {

        Optional<TbPagamento> findByPedido(TbPedido pedido);

    }
}
