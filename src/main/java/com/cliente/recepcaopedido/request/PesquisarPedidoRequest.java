package com.cliente.recepcaopedido.request;

/**
 *
 * @author Jucelio
 */
public class PesquisarPedidoRequest {
    
    /**
     * Utilize o parâmetro ID para pesquisar os pedidos pelo ID da tabela;
     */
    public static final String ID = "id";
    
    /**
     * Utilize o parâmetro DATA_CADASTRO para pesquisar os pedidos pela data de cadastro
     */
    public static final String DATA_CADASTRO = "data cadastro";
    
    /**
     * Utilize o parâmetro NUMERO_CONTROLE para pesquisar os pedidos pelo numero de controle;
     */
    public static final String NUMERO_CONTROLE = "numero controle";
    
    /**
     * Utilize o parâmetro CODIGO_CLIENTE para pesquisar os pedidos de um determinado cliente;
     */
    public static final String CODIGO_CLIENTE = "codigo cliente";
    
    /**
     * Utilize o parâmetro VALOR_TOTAL para pesquisar os pedidos que estejam no valor citado;
     */
    public static final String VALOR_TOTAL = "valor total";
    
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
