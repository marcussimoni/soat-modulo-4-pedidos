package br.com.fiapsoat.db.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.io.Serializable;

@Data
@Entity
@Table(name = "produto")
@SQLDelete(sql = "update produto set ativo = false where id = ?")
@Where(clause = "ativo = true")
@NoArgsConstructor
public class TbProduto implements Serializable {

    public TbProduto(Produto produto){
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.imagem = produto.getImagem();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.descricao = produto.getDescricao();
    }

    @Id
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    @NotBlank(message = "O campo nome não pode ser vazio")
    @Size(min = 0, max = 100, message = "O Campo nome deve conter até 100 caracteres")
    private String nome;

    @Column(name = "descricao")
    @NotBlank(message = "O campo descrição não pode ser vazio")
    @Size(min = 0, max = 300, message = "O Campo descrição deve conter até 300 caracteres")
    private String descricao;

    @Column(name = "preco")
    @NotNull(message = "O campo preço é obrigatório")
    @DecimalMin(value = "0.0", message = "O preço não pode ser negativo")
    private Double preco;

    @Column
    @Enumerated(value = EnumType.STRING)
    @NotNull(message = "O campo categoria não pode ser vazio")
    private Categoria categoria;

    @Column(name = "imagem")
    @NotBlank(message = "O campo imagem não pode ser vazio")
    private String imagem;

    public Produto produtoBuilder(){
        return Produto
                .builder()
                .id(getId())
                .preco(getPreco())
                .categoria(getCategoria())
                .descricao(getDescricao())
                .imagem(getImagem())
                .nome(getNome())
                .build();
    }

}
