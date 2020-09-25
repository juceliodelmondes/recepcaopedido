package com.cliente.recepcaopedido.interfaces.service;

import com.cliente.recepcaopedido.model.PedidoModel;
import java.util.List;

/**
 *
 * @author Jucelio
 */
public interface PedidoServiceInterface {
    public String adicionarPedido(List<PedidoModel> pedido);
}
