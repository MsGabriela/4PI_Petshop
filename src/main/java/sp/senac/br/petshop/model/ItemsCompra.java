package sp.senac.br.petshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "items_compra")
public class ItemsCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idItemsCompra;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Compra compra;

    private int quantidade;

    private double valorUnitario=0.;

    private double valorTotal =0.;

    public int getIdItemsCompra() {
        return idItemsCompra;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void setIdItemsCompra(int idItemsCompra) {
        this.idItemsCompra = idItemsCompra;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public int getQuantidade() {
        if (quantidade <= 0){
            return quantidade = 0;
        }else{
        return quantidade;
        }
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
    
}
