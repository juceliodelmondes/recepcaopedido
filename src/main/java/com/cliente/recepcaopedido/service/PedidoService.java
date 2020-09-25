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
                for(PedidoModel result : pedido) {
                    int quantidadeProdutos = 0;
                    float valorTotalPedido = 0.00f;
                    if(validarData(result.getDataCadastro()) != null) { //data  válida ou nova data caso não o tenha
                        PedidoModel pedidoConsulta = pedidoRepository.findByNumeroControle(result.getNumeroControle());
                        if(pedidoConsulta == null && result.getProdutos() != null) { //não encontrou nenhum pedido com o numero de controle especificado
                            pedidoConsulta = pedidoRepository.save(result);
                            for(ProdutoModel resultProduto : result.getProdutos()) {
                                if(resultProduto.getQuantidade() == 0) resultProduto.setQuantidade(1);
                                quantidadeProdutos+=resultProduto.getQuantidade();
                                valorTotalPedido+=resultProduto.getValor()*resultProduto.getQuantidade();
                                resultProduto.setPedido(result);
                                produtoRepository.save(resultProduto);
                            }
                            if(quantidadeProdutos >= 5 && quantidadeProdutos <= 9) valorTotalPedido = valorTotalPedido-(valorTotalPedido*0.05f);
                                else if(quantidadeProdutos >= 10) valorTotalPedido = valorTotalPedido-(valorTotalPedido*0.10f);
                            pedidoConsulta.setValorTotal(valorTotalPedido);
                            if(pedidoRepository.save(pedidoConsulta) != null) response.setMensagem("Pedido registrado!");
                        }
                    } else response.setMensagem("Data inválida: "+result.getDataCadastro()+" formato correto: (dd/MM/yyyy) - número controle: "+result.getNumeroControle());
                }
            } else response.setMensagem("Limite máximo de pedidos: 10! Total: "+pedido.size());
        } catch (Exception er) {
            response.setMensagem("Erro ao registrar o pedido!");
            System.out.println(er.getMessage());
        }
        return response;
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
