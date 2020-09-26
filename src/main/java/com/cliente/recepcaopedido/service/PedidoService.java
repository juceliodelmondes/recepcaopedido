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
import com.cliente.recepcaopedido.model.ProdutoModel;
import com.cliente.recepcaopedido.response.AdicionarPedidoResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
    public AdicionarPedidoResponse adicionarPedido(List<PedidoModel> pedido) {
        AdicionarPedidoResponse response = new AdicionarPedidoResponse();
        try {
            if(pedido.size() >= 1 && pedido.size() <= 10) {
                pedidosValidos(pedido).forEach(result -> {
                    int quantidadeProdutos = 0;
                    float valorTotalPedido = 0.00f;
                    pedidoRepository.save(result);
                    for(ProdutoModel resultProduto : result.getProdutos()) {
                        quantidadeProdutos+=resultProduto.getQuantidade();
                        valorTotalPedido+=resultProduto.getValor()*resultProduto.getQuantidade();
                        resultProduto.setPedido(result);
                        produtoRepository.save(resultProduto);
                    }
                    if(quantidadeProdutos >= 5 && quantidadeProdutos <= 9) valorTotalPedido-=valorTotalPedido*0.05f;
                        else if(quantidadeProdutos >= 10) valorTotalPedido -=valorTotalPedido*0.10f;
                    result.setValorTotal(valorTotalPedido);
                    if(pedidoRepository.save(result) != null) response.setMensagem("Pedido registrado!");
                });
                    
            } else response.setMensagem("Limite máximo de pedidos: 10! Total: "+pedido.size());
        } catch (Exception er) {
            response.setMensagem("Erro ao registrar o pedido!");
            er.printStackTrace();
        }
        return response;
    }
    
    /**
     * Retorna uma lista de pedidos válidos de acordo com as regras 
     * @param pedidos
     * @return 
     */
    private List<PedidoModel> pedidosValidos(List<PedidoModel> pedidos) {
        List<PedidoModel> pedidosValidos = new ArrayList<>();
        pedidos.forEach(pedidosObj -> {
            try {
                if(validarData(pedidosObj.getDataCadastro()) != null && pedidoRepository.findByNumeroControle(pedidosObj.getNumeroControle()) == null &&
                    pedidosObj.getProdutos() != null) pedidosValidos.add(pedidosObj);
            } catch (Exception er) {
                er.printStackTrace();
            }
            
        });
        return pedidosValidos;
    }
    
    /**
     * Valida uma data
     * Caso a data estiver nulo, retornará a data atual
     * Caso a data não estiver nulo, validar se está no formato (dd/MM/yyyy)
     * @param data
     * @return 
     */
    private String validarData(String data) {
        if(data != null) {
            if(data.length() == 10) {
                DateFormat dataObj = new SimpleDateFormat("dd/MM/yyyy");
                dataObj.setLenient(false);
                try {
                    dataObj.parse(data);
                    return data;
                } catch (Exception ex) { return null; }
            } else return null;
        } else {
            Calendar dataObj = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            return dateFormat.format(dataObj.getTime());
        }
    }
}
