/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.service;

import com.cliente.recepcaopedido.interfaces.repository.PedidoRepository;
import com.cliente.recepcaopedido.interfaces.repository.ProdutoRepository;
import com.cliente.recepcaopedido.interfaces.service.ProdutoServiceInterface;
import com.cliente.recepcaopedido.model.PedidoModel;
import com.cliente.recepcaopedido.model.ProdutoModel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jucelio
 */
@Service
public class ProdutoService implements ProdutoServiceInterface {
    
    @Autowired
    PedidoRepository pedidoRepository;
    
    @Autowired
    ProdutoRepository produtoRepository;
    
    
    /**
     * Verifica se todos os produtos são válidos
     * Todos os produtos precisam ser válidos para gravar um pedido
     * @param produtos
     * @return 
     */
    @Override
    public boolean validaProdutos(List<ProdutoModel> produtos) {
        boolean retorno = true;
        for(ProdutoModel produtoAtual : produtos) {
            if(produtoAtual.getNome() == null || produtoAtual.getValor() <= 0) {
                retorno = false;
                break; 
            }
        }
        return retorno;
    }
    
    
    /**
     * Salva os produtos de um determinado pedido
     * @param pedido
     * @return
     * @throws Exception 
     */
    @Override
    public PedidoModel salvarProdutos(PedidoModel pedido) throws Exception {
        int quantidadeProdutos = 0;
        float valorTotalPedido = 0.00f;
        for(ProdutoModel produtoAtual : pedido.getProdutos()) {
            if(produtoAtual.getQuantidade() == 0) produtoAtual.setQuantidade(1);
            quantidadeProdutos+=produtoAtual.getQuantidade();
            valorTotalPedido+=produtoAtual.getValor()*produtoAtual.getQuantidade();
            produtoAtual.setPedido(pedido);
            produtoRepository.save(produtoAtual);
        }
        if(quantidadeProdutos >= 5 && quantidadeProdutos <= 9) valorTotalPedido-=valorTotalPedido*0.05f;
            else if(quantidadeProdutos >= 10) valorTotalPedido -=valorTotalPedido*0.10f;
        pedido.setValorTotal(valorTotalPedido);
        return pedidoRepository.save(pedido);
    }
}
