/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cliente.recepcaopedido.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


/**
 *
 * @author Jucelio
 */
@Entity
public class PedidoModel {
    
    @Id
    private int id;
    private String dataCadastro;
    @Column(length = 10)
    private int numeroControle;
    @OneToMany
    private List<ProdutoModel> produtos;
    
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
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
    
    public List<ProdutoModel> getProdutos() {
        return this.produtos;
    }
    
    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }  
}
