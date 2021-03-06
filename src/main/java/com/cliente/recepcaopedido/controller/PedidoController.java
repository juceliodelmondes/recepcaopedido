/*
    Api para recebimento de pedidos
*/
package com.cliente.recepcaopedido.controller;

import com.cliente.recepcaopedido.interfaces.repository.PedidoRepository;
import com.cliente.recepcaopedido.model.PedidoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cliente.recepcaopedido.interfaces.service.PedidoServiceInterface;
import com.cliente.recepcaopedido.response.AdicionarPedidoResponse;
import com.cliente.recepcaopedido.request.PesquisarPedidoRequest;
import java.util.List;
import org.springframework.http.MediaType;
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
    @PostMapping(value = "/adicionar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AdicionarPedidoResponse adicionar(@RequestBody List<PedidoModel> pedido) {
        return pedidoInterface.adicionarPedido(pedido);
    }
    
    /**
     * Adiciona novos pedidos por XML
     * @param pedido
     * @return  
     */
    @PostMapping(value = "/adicionar", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public AdicionarPedidoResponse XMLadicionar(@RequestBody List<PedidoModel> pedido) {
        return pedidoInterface.adicionarPedido(pedido);
    }
    
    /**
     * Pesquisa por pedidos no banco de dados e retorna em JSON
     * @param pesquisa objeto em JSON
     * @return JSON
     */
    @PostMapping(value = "/pesquisar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PedidoModel> pesquisar(@RequestBody PesquisarPedidoRequest pesquisa) {
        return pedidoInterface.pesquisarPedido(pesquisa);
    }
    
    /**
     * Pesquisa por pedidos no banco de dados e retorna em XML
     * @param pesquisa objeto em XML
     * @return XML
     */
    @PostMapping(value = "/pesquisar", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public List<PedidoModel> XMLpesquisar(@RequestBody PesquisarPedidoRequest pesquisa) {
        return pedidoInterface.pesquisarPedido(pesquisa);
    }
}