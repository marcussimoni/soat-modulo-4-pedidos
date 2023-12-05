package br.com.fiapsoat.usecases.inputports.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;

import java.util.List;

public interface ProdutoInputPort {
    List<Produto> findByCategoria(Categoria categoria);

    Produto save(Produto produto);

    void delete(Produto produto);

    Produto findById(Long id);
}
