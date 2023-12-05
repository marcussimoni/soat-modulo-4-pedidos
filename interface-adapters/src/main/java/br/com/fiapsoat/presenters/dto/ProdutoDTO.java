package br.com.fiapsoat.presenters.dto;


import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.entities.produto.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Produto")
public class ProdutoDTO {

    @Schema(description = "Identificador do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Refrigerante")
    private String nome;

    @Schema(description = "Valor do produto", example = "10.0")
    private Double preco;

    @Schema(description = "Descrição do produto", example = "Refrigerante lata 200ml")
    private String descricao;

    @Schema(description = "Categoria do produto", example = "BEBIDA")
    private Categoria categoria;

    @Schema(description = "Link com imagem do produto", example = "http://imagem.com")
    private String imagem;

    public ProdutoDTO(Produto p){
        id = p.getId();
        nome = p.getNome();
        preco = p.getPreco();
        descricao = p.getDescricao();
        categoria = p.getCategoria();
        imagem = p.getImagem();
    }

}
