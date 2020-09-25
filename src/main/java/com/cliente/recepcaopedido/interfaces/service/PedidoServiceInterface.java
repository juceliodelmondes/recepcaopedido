package com.cliente.recepcaopedido.interfaces.service;

import com.cliente.recepcaopedido.model.PedidoModel;
import com.cliente.recepcaopedido.response.AdicionarPedidoResponse;
import java.util.List;

/**
 *
 * @author Jucelio
 */
public interface PedidoServiceInterface {
    public AdicionarPedidoResponse adicionarPedido(List<PedidoModel> pedido);
}
