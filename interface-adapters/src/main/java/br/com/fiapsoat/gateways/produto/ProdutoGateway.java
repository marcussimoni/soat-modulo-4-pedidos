package br.com.fiapsoat.gateways.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;

import java.util.List;

public interface ProdutoGateway {
    List<Produto> findByCategoria(Categoria categoria);

    Produto save(Produto entity);

    void delete(Produto produto);

    Produto findById(Long id);

}
