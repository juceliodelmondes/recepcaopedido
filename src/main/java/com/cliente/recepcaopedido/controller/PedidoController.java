/*
    Api para recebimento de pedidos
*/
package com.cliente.recepcaopedido.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
    
    @PostMapping("/adicionar")
    public void adicionarPedido() {
        
    }
}
