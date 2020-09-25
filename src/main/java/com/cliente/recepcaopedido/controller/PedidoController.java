/*
    Api para recebimento de pedidos
*/
package com.cliente.recepcaopedido.controller;

import com.cliente.recepcaopedido.interfaces.repository.PedidoRepository;
import com.cliente.recepcaopedido.interfaces.repository.ProdutoRepository;
import com.cliente.recepcaopedido.model.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    PedidoRepository pedidoRepo;
    
    @Autowired
    ProdutoRepository produtoRepo;
    
    @PostMapping("/adicionar")
    public void adicionarPedido(@RequestBody PedidoModel pedido) {
        
    }
}
