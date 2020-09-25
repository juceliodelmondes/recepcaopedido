/*
    Api para recebimento de pedidos
*/
package com.cliente.recepcaopedido.controller;

import com.cliente.recepcaopedido.model.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cliente.recepcaopedido.interfaces.service.PedidoServiceInterface;
import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    PedidoServiceInterface pedidoInterface;
    
    @PostMapping("/adicionar")
    public String adicionarPedido(@RequestBody List<PedidoModel> pedido) {
        return pedidoInterface.adicionarPedido(pedido);
    }
}
