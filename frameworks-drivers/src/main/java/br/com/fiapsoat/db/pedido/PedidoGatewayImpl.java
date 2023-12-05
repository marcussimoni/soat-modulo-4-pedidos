package br.com.fiapsoat.db.pedido;

import br.com.fiapsoat.db.produto.TbProduto;
import br.com.fiapsoat.entities.enums.EtapaDoPedido;
import br.com.fiapsoat.entities.enums.StatusDoPagamento;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.gateways.pedido.PedidoGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static br.com.fiapsoat.entities.enums.EtapaDoPedido.*;

@Repository
@AllArgsConstructor
public class PedidoGatewayImpl implements PedidoGateway {

    private final SpringRepository repository;

    @Override
    public List<Pedido> buscarPedidosDisponiveis(StatusDoPagamento statusDoPagamento) {
        return List.of(PRONTO, EM_PREPARACAO, RECEBIDO).stream().map(etapaDoPedido -> {
            return repository.buscarPedidosDisponiveis(statusDoPagamento, etapaDoPedido).stream().map(this::pedidoBuilder).toList();
        }).flatMap(Collection::stream).toList();
    }

    @Override
    public List<Pedido> buscarTodosOsPedidos(){
        return repository.findAll().stream().map(this::pedidoBuilder).toList();
    }

    @Override
    public Pedido save(Pedido pedido) {

        TbPedido pedidoSalvo = repository.save(new TbPedido(pedido));

        return pedidoBuilder(pedidoSalvo);

    }

    @Override
    public Pedido findById(Long idDoPedido) {

        TbPedido pedido = repository.findById(idDoPedido).orElseThrow(() -> {
            throw new BusinessException("Pedido não encontrado", List.of(MessageFormat.format("Pedido não encontrado para o id {0}", idDoPedido)), HttpStatus.BAD_REQUEST);
        });

        return pedidoBuilder(pedido);
    }

    private Pedido pedidoBuilder(TbPedido pedido){
        Pedido p = new Pedido();
                p.setId(pedido.getId());
                p.setPedidoRealizadoEm(pedido.getPedidoRealizadoEm());
                p.setPedidoRetirado(pedido.getPedidoRetirado());
                p.setEtapa(pedido.getEtapa());
                p.setStatusDoPagamento(pedido.getStatusDoPagamento());
                if(pedido.getCliente() != null){
                    p.setCliente(pedido.getCliente().clienteBuilder());
                }
                p.setProdutos(pedido.getProdutos().stream().map(TbProduto::produtoBuilder).toList());
        return p;
    }

    private interface SpringRepository extends JpaRepository<TbPedido, Long> {
        @Query("SELECT p FROM TbPedido p WHERE p.pedidoRetirado IS NULL " +
                "AND (p.statusDoPagamento = :statusDoPagamento OR :statusDoPagamento IS NULL)" +
                "AND p.etapa = :etapa " +
                "ORDER BY p.pedidoRealizadoEm ASC")
        List<TbPedido> buscarPedidosDisponiveis(@Param("statusDoPagamento") StatusDoPagamento statusDoPagamento, @Param("etapa") EtapaDoPedido etapa);
    }

}
