package sp.senac.br.petshop.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import javax.persistence.*;


@Entity
@Table(name = "pedido")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "idcliente", nullable = false
    )
    Usuario cliente;
    
    int idTipoPagamento;    
    
    int idEndereco;

    int status;
    
    LocalDateTime data;    
    BigDecimal precoVenda;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "detalhes_pedido",
            joinColumns = @JoinColumn(name = "idPedido"),
            inverseJoinColumns = @JoinColumn(name = "idProduto")
    )
    private List<Produto> produtos;
    
    public Pedido()
    {
        
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }
    
    
    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public int getIdTipoPagamento() {
        return idTipoPagamento;
    }

    public void setIdTipoPagamento(int idTipoPagamento) {
        this.idTipoPagamento = idTipoPagamento;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }




    public BigDecimal getPrecoVenda() {
        return precoVenda;
    }

    public void setPrecoVenda(BigDecimal precoVenda) {
        this.precoVenda = precoVenda;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    
    
    
}
