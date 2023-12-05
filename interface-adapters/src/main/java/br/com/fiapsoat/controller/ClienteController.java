package br.com.fiapsoat.controller;

import br.com.fiapsoat.presenters.dto.ClienteDTO;

import java.util.List;

public interface ClienteController {

    List<ClienteDTO> listar();

    ClienteDTO salvar(ClienteDTO dto);

}
