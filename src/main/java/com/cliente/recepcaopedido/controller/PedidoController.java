/*
    Api para recebimento de pedidos
*/
package com.cliente.recepcaopedido.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @PostMapping("/adicionar")
    public void adicionarPedido() {
        
    }
}
