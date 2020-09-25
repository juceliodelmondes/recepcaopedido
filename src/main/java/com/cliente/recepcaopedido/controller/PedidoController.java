/*
    Api para recebimento de pedidos
*/
package com.cliente.recepcaopedido.controller;

import com.cliente.recepcaopedido.model.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cliente.recepcaopedido.interfaces.service.PedidoServiceInterface;
import com.cliente.recepcaopedido.response.AdicionarPedidoResponse;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    PedidoServiceInterface pedidoInterface;
    
    @RequestMapping(value = "/adicionar", method = RequestMethod.POST)
    public AdicionarPedidoResponse adicionarPedido(@RequestBody List<PedidoModel> pedido) {
        return pedidoInterface.adicionarPedido(pedido);
    }
}