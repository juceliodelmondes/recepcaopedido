/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.service;

import com.cliente.recepcaopedido.interfaces.repository.PedidoRepository;
import com.cliente.recepcaopedido.interfaces.repository.ProdutoRepository;
import com.cliente.recepcaopedido.interfaces.service.PedidoServiceInterface;
import com.cliente.recepcaopedido.model.PedidoModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jucelio
 */
@Service
public class PedidoService implements PedidoServiceInterface {
    
    @Autowired
    PedidoRepository pedidoRepository;
    
    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    public String adicionarPedido(List<PedidoModel> pedido) {
        if(pedido.size() >= 1 && pedido.size() <= 10) {
            for(PedidoModel result : pedido) {
                PedidoModel pedidoConsulta = pedidoRepository.findByNumeroControle(result.getNumeroControle());
                if(pedidoConsulta == null) { //não encontrou nenhum pedido com o numero de controle especificado
                    pedidoConsulta = pedidoRepository.save(result);
                    System.out.println("Pedido salvo: "+pedidoConsulta.getId());
                }
            }
            return "fim";
        } else return "Limite máximo de pedidos: 10! Total: "+pedido.size();
    }
    
}
