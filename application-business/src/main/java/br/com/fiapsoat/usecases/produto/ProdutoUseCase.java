package br.com.fiapsoat.usecases.produto;

import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.enums.Categoria;

import java.util.List;

public interface ProdutoUseCase {
    List<Produto> listar(Categoria categoria);
    Produto salvar(Produto produto);

    Produto atualizar(Produto produto);

    void excluir(Long id);
}
