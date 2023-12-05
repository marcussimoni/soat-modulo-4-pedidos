package br.com.fiapsoat.web.presenter.produto;

import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.presenters.dto.ProdutoDTO;
import br.com.fiapsoat.presenters.produto.ProdutoPresenter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoPresenterImpl implements ProdutoPresenter {


    @Override
    public List<ProdutoDTO> toProdutoDTO(List<Produto> produtos) {
        return produtos.stream().map(ProdutoDTO::new).toList();
    }

    @Override
    public ProdutoDTO toProdutoDTO(Produto produto) {
        return new ProdutoDTO(produto);
    }

    @Override
    public Produto fromProdutoDTO(ProdutoDTO dto) {
        return Produto
                .builder()
                .id(dto.getId())
                .preco(dto.getPreco())
                .categoria(dto.getCategoria())
                .descricao(dto.getDescricao())
                .imagem(dto.getImagem())
                .nome(dto.getNome())
                .build();
    }
}
