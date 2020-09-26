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
import com.cliente.recepcaopedido.request.PesquisarPedidoRequest;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    
    @Autowired
    PedidoServiceInterface pedidoInterface;
    /**
     * Adiciona novos pedidos
     * @param pedido
     * @return 
     */
    @PostMapping("/adicionar")
    public AdicionarPedidoResponse adicionar(@RequestBody List<PedidoModel> pedido) {
        return pedidoInterface.adicionarPedido(pedido);
    }
    /**
     * Pesquisa por pedidos no banco de dados
     * @param id 
     */
    @PostMapping("/pesquisar")
    public List<PedidoModel> pesquisar(@RequestBody PesquisarPedidoRequest pesquisa) {
        return pedidoInterface.pesquisarPedido(pesquisa);
    }
}