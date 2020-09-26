/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.request;

/**
 *
 * @author Jucelio
 */
public class PesquisarPedidoRequest {
    private String atributo;
    private String valor;
    
    public String getAtributo() {
        return this.atributo;
    }
    
    public void setAtributo(String atributo) {
        this.atributo = atributo;
    }
    
    public String getValor() {
        return this.valor;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
}
