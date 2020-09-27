package com.cliente.recepcaopedido.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
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
    @Column(nullable = false)
    private String nome;
    private BigDecimal valor;
    @Column(nullable = false)
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
    
    public BigDecimal getValor() {
        return this.valor;
    }
    
    public void setValor(BigDecimal valor) {
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
