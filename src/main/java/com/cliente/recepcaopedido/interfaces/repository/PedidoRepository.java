/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.interfaces.repository;

import com.cliente.recepcaopedido.model.PedidoModel;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Jucelio
 */
public interface PedidoRepository extends CrudRepository<PedidoModel, Integer>{
    public List<PedidoModel> findAllByDataCadastro(String data);
    public PedidoModel findByNumeroControle(int numeroControle);
    public List<PedidoModel> findAllByCodigoCliente(int codigoCliente);
    public List<PedidoModel> findAllByValorTotal(float valorTotal);
}
