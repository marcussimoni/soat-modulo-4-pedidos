package br.com.fiapsoat.entities.produto;

import br.com.fiapsoat.entities.enums.Categoria;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
public class Produto implements Serializable {

    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

    private Categoria categoria;

    private String imagem;

}
