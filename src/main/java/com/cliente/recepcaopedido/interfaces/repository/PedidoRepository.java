/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.interfaces.repository;

import com.cliente.recepcaopedido.model.PedidoModel;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Jucelio
 */
public interface PedidoRepository extends CrudRepository<PedidoModel, Integer>{
    
}
