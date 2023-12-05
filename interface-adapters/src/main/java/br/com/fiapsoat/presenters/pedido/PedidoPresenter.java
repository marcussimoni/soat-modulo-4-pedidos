package br.com.fiapsoat.presenters.pedido;

import br.com.fiapsoat.entities.pedido.Pedido;
import br.com.fiapsoat.presenters.dto.PedidoDTO;

public interface PedidoPresenter {

    PedidoDTO pedidoDTOBuilder(Pedido pedido);

}
