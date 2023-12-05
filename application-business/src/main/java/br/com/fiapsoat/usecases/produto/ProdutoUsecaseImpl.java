package br.com.fiapsoat.usecases.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.usecases.inputports.produto.ProdutoInputPort;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoUsecaseImpl implements ProdutoUseCase {

    private final ProdutoInputPort produtoInputPort;

    @Override
    public List<Produto> listar(Categoria categoria) {
        return produtoInputPort.findByCategoria(categoria);
    }

    @Override
    @Transactional
    public Produto salvar(Produto produto) {

        return produtoInputPort.save(produto);

    }

    @Override
    @Transactional
    public Produto atualizar(Produto produto) {

        findById(produto.getId());

        return produtoInputPort.save(produto);

    }

    @Override
    public void excluir(Long id) {
        Produto produto = findById(id);
        produtoInputPort.delete(produto);
    }

    private Produto findById(Long id) {
        return produtoInputPort.findById(id);
    }
}
