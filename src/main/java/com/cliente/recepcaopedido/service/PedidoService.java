/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.service;

import com.cliente.recepcaopedido.interfaces.repository.PedidoRepository;
import com.cliente.recepcaopedido.interfaces.repository.ProdutoRepository;
import com.cliente.recepcaopedido.interfaces.service.PedidoServiceInterface;
import com.cliente.recepcaopedido.interfaces.service.ProdutoServiceInterface;
import com.cliente.recepcaopedido.model.PedidoModel;
import com.cliente.recepcaopedido.model.ProdutoModel;
import com.cliente.recepcaopedido.response.AdicionarPedidoResponse;
import com.cliente.recepcaopedido.request.PesquisarPedidoRequest;
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
    ProdutoServiceInterface produtoInterface;
    
    /**
     * Retorna uma lista de pedidos válidos de acordo com as regras 
     * @param pedidos
     * @return 
     */
    private List<PedidoModel> pedidosValidos(List<PedidoModel> pedidos) {
        List<PedidoModel> pedidosValidos = new ArrayList<>();
        pedidos.forEach(pedidosObj -> {
            try {
                if(validarData(pedidosObj.getDataCadastro()) != null && pedidosObj.getNumeroControle() != 0 &&
                        pedidoRepository.findByNumeroControle(pedidosObj.getNumeroControle()) == null && pedidosObj.getProdutos() != null) pedidosValidos.add(pedidosObj);
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
    
    //Métodos abstratos
    
    /**
     * Adiciona um novo pedido no banco de dados (solicitação do cliente)
     * @param pedido
     * @return 
     */
    @Override
    public AdicionarPedidoResponse adicionarPedido(List<PedidoModel> pedido) {
        AdicionarPedidoResponse response = new AdicionarPedidoResponse();
        List<Integer> pedidosRegistrados = new ArrayList<>();
        if(pedido.size() >= 1 && pedido.size() <= 10) {
            this.pedidosValidos(pedido).forEach(result -> {
                if(produtoInterface.validaProdutos(result.getProdutos())) {
                    try {
                        result = pedidoRepository.save(result);
                        produtoInterface.salvarProdutos(result);
                        pedidosRegistrados.add(result.getNumeroControle());
                    } catch(Exception er) {
                        er.printStackTrace();
                    }
                }
            });
        } else response.setMensagem("Limite máximo de pedidos: 10! Total: "+pedido.size());
        response.setPedidosCadastrados(pedidosRegistrados);
        return response;
    }    
    
    /**
     * Pesquisa um determinado pedido de acordo com o atributo e valor
     * @param pedido objeto PesquisarPedidoRequest com parâmetros: atributo e valor
     * Valores possíveis de atributos:
     * "id",
     * "data cadastro",
     * "numero controle",
     * "codigo cliente",
     * "valor total"
     * 
     * Exemplo de parâmetro JSON:
     * {
     *      "atributo" : "id",
     *      "valor" : 5
     * }
     * 
     * @return lista de pedidos
     */    
    @Override
    public List<PedidoModel> pesquisarPedido(PesquisarPedidoRequest pedido) {
        List<PedidoModel> pedidos = new ArrayList<>();
        try {
            switch(pedido.getAtributo()) {
                case PesquisarPedidoRequest.ID:
                    PedidoModel result = pedidoRepository.findById(Integer.parseInt(pedido.getValor())).get();
                    if(result != null) pedidos.add(result);
                    break;
                case PesquisarPedidoRequest.DATA_CADASTRO:
                    pedidos = pedidoRepository.findAllByDataCadastro(pedido.getValor());
                    break;
                case PesquisarPedidoRequest.NUMERO_CONTROLE:
                    PedidoModel resultn = pedidoRepository.findByNumeroControle(Integer.parseInt(pedido.getValor()));
                    if(resultn != null) pedidos.add(resultn);
                    break;
                case PesquisarPedidoRequest.CODIGO_CLIENTE:
                    pedidos = pedidoRepository.findAllByCodigoCliente(Integer.parseInt(pedido.getValor()));
                    break;
                case PesquisarPedidoRequest.VALOR_TOTAL: 
                    pedidos = pedidoRepository.findAllByValorTotal(Float.parseFloat(pedido.getValor()));
                    break;
                default:
                    
                    break;
            }
            pedidos.forEach(pedidoAtual -> {
                pedidoAtual.getProdutos().forEach(produtoAtual -> {
                    produtoAtual.setPedido(null); //evita loop de tabela com relacionamento bidirecional
                });
            });
        } catch(Exception er) {
            er.printStackTrace();
        }
        return pedidos;
    }
}
