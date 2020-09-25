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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public String adicionarPedido(List<PedidoModel> pedido) {
        if(pedido.size() >= 1 && pedido.size() <= 10) {
            for(PedidoModel result : pedido) {
                System.out.println("pedidos "+pedido.size());
                System.out.println("Pedido atual "+result.getId());
                String data = validarData(result.getDataCadastro());
                System.out.println("DATA "+data);
                if(data != null) { //data  válida ou nova data caso não o tenha
                    PedidoModel pedidoConsulta = pedidoRepository.findByNumeroControle(result.getNumeroControle());
                    if(pedidoConsulta == null) { //não encontrou nenhum pedido com o numero de controle especificado
                        result.setDataCadastro(data);
                        pedidoConsulta = pedidoRepository.save(result);
                        System.out.println("Pedido salvo: "+pedidoConsulta.getId());
                    }
                    System.out.println("Pedido encontrado: "+pedidoConsulta.getId());
                } else return "Data inválida: "+result.getDataCadastro()+" formato correto: (dd/MM/yyyy) - número controle: "+result.getNumeroControle();
            }
            return "fim";
        } else return "Limite máximo de pedidos: 10! Total: "+pedido.size();
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
