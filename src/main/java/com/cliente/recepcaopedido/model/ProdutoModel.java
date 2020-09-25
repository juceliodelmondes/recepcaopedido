/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author Jucelio
 */
@Entity
public class ProdutoModel {
    
    @Id
    private int id;
    private String nome;
    private float valor;
    @ManyToOne
    private PedidoModel pedido;
    
    public int getId() { 
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public float getValor() {
        return this.valor;
    }
    
    public void setValor(float valor) {
        this.valor = valor;
    }
    
    public PedidoModel getPedido () {
        return this.pedido;
    }
    
    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }
    
}
