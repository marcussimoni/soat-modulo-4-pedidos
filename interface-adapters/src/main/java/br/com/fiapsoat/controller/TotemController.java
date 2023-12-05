package br.com.fiapsoat.controller;

import br.com.fiapsoat.presenters.dto.*;
import br.com.fiapsoat.entities.enums.Categoria;

import java.util.List;

public interface TotemController {

    ClienteDTO buscarClientePorCpf(String cpf);

    ClienteDTO cadastrarNovoCliente(ClienteDTO dto);

    List<ProdutoDTO> buscarProdutosPorCategoria(Categoria categoria);

    PedidoDTO checkout(CheckoutPedidoDTO dto);

}
