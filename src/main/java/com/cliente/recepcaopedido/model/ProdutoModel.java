/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 *
 * @author Jucelio
 */
@Entity
public class ProdutoModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;
    private String nome;
    private float valor;
    private int quantidade;
    @ManyToOne
    private PedidoModel pedido; 
    
    public Integer getId() { 
        return id;
    }

    public void setId(Integer id) {
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
    
    public int getQuantidade() {
        return this.quantidade;
    }
    
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    
    public PedidoModel getPedido () {
        return this.pedido;
    }
    
    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }
    
}
