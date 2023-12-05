package br.com.fiapsoat.presenters.produto;

import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.presenters.dto.ProdutoDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

public interface ProdutoPresenter {

    List<ProdutoDTO> toProdutoDTO(List<Produto> produtos);

    ProdutoDTO toProdutoDTO(Produto produto);

    Produto fromProdutoDTO(ProdutoDTO dto);

}
