package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.controller.TotemController;
import br.com.fiapsoat.entities.cliente.Cliente;
import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.recibo.Comprovante;
import br.com.fiapsoat.entities.valueobjects.cpf.Cpf;
import br.com.fiapsoat.entities.valueobjects.email.Email;
import br.com.fiapsoat.entities.valueobjects.nome.Nome;
import br.com.fiapsoat.presenters.cliente.ClientePresenter;
import br.com.fiapsoat.presenters.dto.*;
import br.com.fiapsoat.presenters.pedido.PedidoPresenter;
import br.com.fiapsoat.presenters.produto.ProdutoPresenter;
import br.com.fiapsoat.usecases.cliente.ClienteUseCase;
import br.com.fiapsoat.usecases.pagamento.PagamentoUseCase;
import br.com.fiapsoat.usecases.pedido.PedidoUseCase;
import br.com.fiapsoat.usecases.produto.ProdutoUseCase;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("atendimento")
@AllArgsConstructor
@Tag(description = "Apresenta etapas para o cliente realizar o pedido", name = "Totem de auto atendimento")
public class TotemControllerImpl implements TotemController {

    private final ClienteUseCase clienteUseCase;
    private final ClientePresenter clientePresenter;
    private final ProdutoUseCase produtoUseCase;
    private final ProdutoPresenter produtoPresenter;
    private final PedidoUseCase pedidoUseCase;
    private final PedidoPresenter pedidoPresenter;

    @Override
    @GetMapping(path = "/cliente/buscar")
    @Operation(summary = "Buscar cliente por cpf", tags = "Totem de auto atendimento", description = "Consulta na base de dados um cliente cadastrado a partir do cpf")
    public ClienteDTO buscarClientePorCpf(
            @Parameter(description = "Cpf do cliente para identificação")
            @RequestHeader String cpf
    ) {
        return clientePresenter.toClienteDTO(clienteUseCase.buscarClientePorCpf(cpf));
    }

    @Override
    @PostMapping(path = "/cliente/cadastrar")
    @Operation(summary = "Cadastrar novo cliente", tags = "Totem de auto atendimento", description = "Cadastrar um novo cliente caso ainda não seja cadastrado na base de dados. Não pode existir mais de um cliente com o mesmo cpf")
    public ClienteDTO cadastrarNovoCliente(
            @Parameter(description = "Dados do cliente")
            @RequestBody ClienteDTO dto
    ) {
        Cliente cliente = Cliente.builder()
                .id(dto.getId())
                .cpf(new Cpf(dto.getCpf()))
                .email(new Email(dto.getEmail()))
                .nome(new Nome(dto.getNome()))
                .build();
        return clientePresenter.toClienteDTO(clienteUseCase.salvar(cliente));
    }

    @Override
    @GetMapping(path = "/produtos")
    @Operation(tags = "Totem de auto atendimento", summary = "Buscar produtos por categoria", description = "Lista todos os produtos disponíveis para o pedido ordenados por categoria.")
    public List<ProdutoDTO> buscarProdutosPorCategoria(
            @RequestParam(name = "categoria", required = false) Categoria categoria){
        return produtoPresenter.toProdutoDTO(produtoUseCase.listar(categoria));
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(path = "/pedido/checkout")
    @Operation(tags = "Totem de auto atendimento", summary = "Checkout do pedido", description = "Realiza o checkout do pedido com todos os itens selecionados e os dados do cliente caso o mesmo tenha se identificado. É obrigatório a adicionar ao menos um produto a lista para prosseguir com o checkout do pedido.")
    public PedidoDTO checkout(@RequestBody CheckoutPedidoDTO dto){
        List<Produto> produtos = dto.getProdutos().stream().map(produtoPresenter::fromProdutoDTO).toList();
        Pedido pedido = pedidoUseCase.checkoutPedido(produtos);
        return pedidoPresenter.pedidoDTOBuilder(pedido);
    }

}
