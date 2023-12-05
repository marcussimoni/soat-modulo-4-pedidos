package br.com.fiapsoat.web.controller;

import br.com.fiapsoat.controller.ProdutoController;
import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.presenters.dto.ProdutoDTO;
import br.com.fiapsoat.presenters.produto.ProdutoPresenter;
import br.com.fiapsoat.usecases.produto.ProdutoUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("produto")
@Tag(name = "Área administrativa - Produtos", description = "Gerencia os produtos disponíveis para pedidos")
public class ProdutoControllerImpl implements ProdutoController {

    private final ProdutoUseCase produtoUseCase;
    private final ProdutoPresenter produtoPresenter;

    @Override
    @GetMapping
    @Operation(tags = "Área administrativa - Produtos", summary = "Listar produtos", description = "Lista todos os produtos disponíveis")
    public List<ProdutoDTO> listar(
            @RequestParam(name = "categoria", required = false) Categoria categoria){
        return produtoPresenter.toProdutoDTO(produtoUseCase.listar(categoria));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(tags = "Área administrativa - Produtos", description = "Cadastra um novo produto", summary = "Cadastrar novo produto")
    public ProdutoDTO salvar(@RequestBody ProdutoDTO dto) {
        Produto produto = produtoPresenter.fromProdutoDTO(dto);
        return produtoPresenter.toProdutoDTO(produtoUseCase.salvar(produto));
    }


    @Override
    @PutMapping(path = "{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(tags = "Área administrativa - Produtos", description = "Atualizar um produto", summary = "Atualizar produto")
    public ProdutoDTO atualizar(@RequestBody ProdutoDTO dto, @PathVariable Long id) {
        Produto produto = produtoPresenter.fromProdutoDTO(dto);
        produto.setId(id);
        return produtoPresenter.toProdutoDTO(produtoUseCase.atualizar(produto));
    }

    @Override
    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(tags = "Área administrativa - Produtos", description = "Excluir um produto", summary = "Excluir produto")
    public void excluir(@PathVariable Long id) {
        produtoUseCase.excluir(id);
    }

}
