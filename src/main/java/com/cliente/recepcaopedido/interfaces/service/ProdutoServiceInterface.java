/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.interfaces.service;

import com.cliente.recepcaopedido.model.PedidoModel;
import com.cliente.recepcaopedido.model.ProdutoModel;
import java.util.List;

/**
 *
 * @author Jucelio
 */
public interface ProdutoServiceInterface {
    public PedidoModel salvarProdutos(PedidoModel pedido) throws Exception;
    public boolean validaProdutos(List<ProdutoModel> produtos);
}
