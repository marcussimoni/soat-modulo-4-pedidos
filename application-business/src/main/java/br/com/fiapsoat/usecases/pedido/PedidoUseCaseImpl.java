package br.com.fiapsoat.usecases.pedido;

import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.usecases.auth.AuthUseCase;
import br.com.fiapsoat.usecases.inputports.pedido.PedidoInputPort;
import br.com.fiapsoat.usecases.cliente.ClienteUseCase;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.MessageFormat;
import java.util.List;

import static br.com.fiapsoat.entities.enums.StatusDoPagamento.AGUARDANDO_PAGAMENTO;

@Service
@AllArgsConstructor
public class PedidoUseCaseImpl implements PedidoUseCase {

    private final PedidoInputPort pedidoInputPort;
    private final ClienteUseCase clienteUseCase;

    @Override
    public Pedido buscarPedidoPorId(Long id) {

        return pedidoInputPort.findById(id);

    }

    @Override
    public Pedido checkoutPedido(List<Produto> produtos) {

        Cliente cliente = null;

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String cpf = principal.getUsername();

        if (!cpf.equalsIgnoreCase("cliente_nao_cadastrado")) {
            cliente = clienteUseCase.buscarClientePorCpf(cpf);
        }

        if (CollectionUtils.isEmpty(produtos)){
            throw new BusinessException("Nenhum produto selecionado", List.of("Ao menos um produto deve ser adicionado a lista de produtos para prosseguir com o checkout do pedido"), HttpStatus.BAD_REQUEST);
        }

        return pedidoInputPort.save(new Pedido(cliente, produtos));

    }

    @Override
    @Transactional
    public void atualizarStatusPagamentoDoPedido(Long id, StatusDoPagamento status) {
        Pedido pedido = pedidoInputPort.findById(id);
        pedido.setStatusDoPagamento(status);
        pedidoInputPort.save(pedido);
    }


    @Override
    public List<Pedido> listar(StatusDoPagamento statusDoPagamento) {

//        if(statusDoPagamento == null) {
//            return pedidoService.buscarTodosOsPedidosDisponiveis();
//        } else {
            return pedidoInputPort.buscarPedidosDisponiveis(statusDoPagamento);
//        }

    }

    @Transactional
    public Pedido atualizaParaAProximaEtapaDoPedido(Long pedido) {

        Pedido entity = pedidoInputPort.findById(pedido);

        if(entity.getStatusDoPagamento() == AGUARDANDO_PAGAMENTO){
            String mensagem = "Não foi possível atualizar a etapa do pedido";
            List<String> detalhes = List.of(MessageFormat.format("O pedido {0} encontra-se com o status de pagamento {1}. Só será possível atualizar a etapa de pedido com o pagamento confirmado.", pedido, entity.getStatusDoPagamento().getStatus()));
            throw new BusinessException(mensagem, detalhes, HttpStatus.BAD_REQUEST);
        }

        entity.setEtapa(entity.getEtapa().proximaEtapa());

        return pedidoInputPort.save(entity);

    }

}
