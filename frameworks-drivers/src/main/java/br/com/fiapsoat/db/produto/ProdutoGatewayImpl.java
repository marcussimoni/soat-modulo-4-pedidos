package br.com.fiapsoat.db.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import br.com.fiapsoat.entities.exceptions.BusinessException;
import br.com.fiapsoat.gateways.produto.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@AllArgsConstructor
public class ProdutoGatewayImpl implements ProdutoGateway {

    private final SpringRepository repository;

    @Override
    public List<Produto> findByCategoria(Categoria categoria) {
        List<TbProduto> produtos = repository.findByCategoria(categoria);
        return produtos.stream().map(TbProduto::produtoBuilder).toList();
    }

    @Override
    public Produto save(Produto entity) {
        TbProduto produto = new TbProduto(entity);
        TbProduto produtoSalvo = repository.save(produto);
        return produtoSalvo.produtoBuilder();
    }

    @Override
    public void delete(Produto produto) {
        repository.delete(new TbProduto(produto));
    }

    @Override
    public Produto findById(Long id) {
        TbProduto produto = repository.findById(id).orElseThrow(() -> {
            throw new BusinessException("Produto não encontrado para o id " + id, List.of("Produto não encontrado para o id " + id), HttpStatus.NOT_FOUND);
        });

        return produto.produtoBuilder();
    }

    private interface SpringRepository extends JpaRepository<TbProduto, Long> {

        @Query("SELECT p FROM TbProduto p " +
                "WHERE (p.categoria = :categoria OR :categoria IS NULL)")
        List<TbProduto> findByCategoria(@Param("categoria") Categoria categoria);
    }
}
