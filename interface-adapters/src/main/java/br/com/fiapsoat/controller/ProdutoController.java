package br.com.fiapsoat.controller;

import br.com.fiapsoat.entities.enums.Categoria;
import br.com.fiapsoat.presenters.dto.ProdutoDTO;

import java.util.List;


public interface ProdutoController {

    List<ProdutoDTO> listar(Categoria categoria);

    ProdutoDTO salvar(ProdutoDTO dto);

    ProdutoDTO atualizar(ProdutoDTO dto, Long id);

    void excluir(Long id);

}
