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
