package sp.senac.br.petshop.model;

import java.util.List;

public class Carrinho
{
    private int cliente;
    private int tipoPagamento;
    private float valor;
    private int idEndereco;
    
    private List<ProdutoCarrinho> produtos;

    public int getIdEndereco() 
    {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) 
    {
        this.idEndereco = idEndereco;
    }
    
    

    public int getCliente() 
    {
        return cliente;
    }

    public void setCliente(int cliente)
    {
        this.cliente = cliente;
    }

    public List<ProdutoCarrinho> getProdutos() 
    {
        return produtos;
    }

    public void setProdutos(List<ProdutoCarrinho> produtos) 
    {
        this.produtos = produtos;
    }

    public int getTipoPagamento() 
    {
        return tipoPagamento;
    }

    public void setTipoPagamento(int tipoPagamento) 
    {
        this.tipoPagamento = tipoPagamento;
    }

    public float getValor() 
    {
        return valor;
    }

    public void setValor(float valor) 
    {
        this.valor = valor;
    }
    
}
