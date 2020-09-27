package com.cliente.recepcaopedido.service;

import com.cliente.recepcaopedido.interfaces.repository.PedidoRepository;
import com.cliente.recepcaopedido.interfaces.repository.ProdutoRepository;
import com.cliente.recepcaopedido.interfaces.service.ProdutoServiceInterface;
import com.cliente.recepcaopedido.model.PedidoModel;
import com.cliente.recepcaopedido.model.ProdutoModel;
import java.math.BigDecimal;
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
            if(produtoAtual.getNome() == null || produtoAtual.getValor().compareTo(new BigDecimal(0)) != 1) {
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
        BigDecimal valorTotalPedido = new BigDecimal("0.00");
        for(ProdutoModel produtoAtual : pedido.getProdutos()) {
            if(produtoAtual.getQuantidade() == 0) produtoAtual.setQuantidade(1);
            quantidadeProdutos+=produtoAtual.getQuantidade();
            valorTotalPedido = (produtoAtual.getValor().multiply(new BigDecimal(produtoAtual.getQuantidade())));
            produtoAtual.setPedido(pedido);
            produtoRepository.save(produtoAtual);
        }
        if(quantidadeProdutos >= 5 && quantidadeProdutos <= 9) valorTotalPedido = valorTotalPedido.subtract(valorTotalPedido.multiply(new BigDecimal("0.05")));
            else if(quantidadeProdutos >= 10) valorTotalPedido = valorTotalPedido.subtract(valorTotalPedido.multiply(new BigDecimal("0.10")));
        pedido.setValorTotal(valorTotalPedido);
        return pedidoRepository.save(pedido);
    }
}
