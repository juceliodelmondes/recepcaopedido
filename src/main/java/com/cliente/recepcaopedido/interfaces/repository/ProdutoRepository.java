package com.cliente.recepcaopedido.interfaces.repository;

import com.cliente.recepcaopedido.model.ProdutoModel;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Jucelio
 */
public interface ProdutoRepository extends CrudRepository<ProdutoModel, Integer> {
    
}
