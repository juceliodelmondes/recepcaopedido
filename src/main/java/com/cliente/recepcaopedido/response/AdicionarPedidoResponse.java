
package com.cliente.recepcaopedido.response;

import java.util.List;

public class AdicionarPedidoResponse {
    
    private String mensagem;
    private List<Integer> pedidosCadastrados;
    
    
    public String getMensagem() {
        return this.mensagem;
    }
    
    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public List<Integer> getPedidosCadastrados() {
        return this.pedidosCadastrados;
    }
    
    public void setPedidosCadastrados(List<Integer> pedidosCadastrados) {
        this.pedidosCadastrados = pedidosCadastrados;
    }
    
}
