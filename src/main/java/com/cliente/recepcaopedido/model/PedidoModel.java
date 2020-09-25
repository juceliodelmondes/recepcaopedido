/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author Jucelio
 */
@Entity
public class PedidoModel implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    private Integer id;
    @Column(length = 10)
    private String dataCadastro;
    @Column(length = 10)
    private int numeroControle;
    @Column(length = 10)
    private int codigoCliente;
    private float valorTotal;
    @OneToMany(mappedBy = "pedido")
    private List<ProdutoModel> produtos;
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getDataCadastro() {
        return this.dataCadastro;
    }
    
    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
    
    public int getNumeroControle() {
        return this.numeroControle;
    }
    
    public void setNumeroControle(int numeroControle) {
        this.numeroControle = numeroControle;
    }
    
    public int getCodigoCliente() {
        return this.codigoCliente;
    }
    
    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    public float getValorTotal() {
        return this.valorTotal;
    }
    
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }
    
    public List<ProdutoModel> getProdutos() {
        return this.produtos;
    }
    
    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
