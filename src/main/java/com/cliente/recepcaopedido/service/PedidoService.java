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
    ProdutoRepository produtoRepository;    
    
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
     * Verifica se os produtos são válidos
     * Todos os produtos precisam ser válidos para gravar um pedido
     * @param produtos
     * @return 
     */
    private boolean produtosValidos(List<ProdutoModel> produtos) {
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
    private PedidoModel salvarProdutos(PedidoModel pedido) throws Exception {
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
                if(produtosValidos(result.getProdutos())) {
                    try {
                        result = pedidoRepository.save(result);
                        this.salvarProdutos(result);
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
