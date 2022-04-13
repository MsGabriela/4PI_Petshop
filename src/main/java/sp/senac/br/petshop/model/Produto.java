package sp.senac.br.petshop.model;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.NumberFormat;

@Entity
@Table(name = "produto")
public class Produto implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idProduto;

    @NotBlank(message = "Preencha o nome do produto!")
    @Size(max = 60)
    private String nome;


    @NotNull(message = "Preencha o preço do produto!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 0, message =   "O preço não pode ser negativo")
    private double preco;
    

    @NotEmpty(message = "Preencha o fabricante")
    @Size(max = 60)
    private String fabricante;

    @NotNull(message = "Preencha o estoque!")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 0, message =   "O preço não pode ser negativo")
    private int estoque;

    @NotEmpty(message = "Preencha o modelo!")
    @Size(max = 60)
    private String modelo;

    @NotEmpty(message = "Preencha o código de barras!")
    @Size(max = 45)
    private String codigoDeBarras;

    @NotEmpty(message = "Preencha a Descrição!")
    @Size(max = 150)
    private String descricao;

    @NotNull(message = "Preencha o campo desconto")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Min(value = 0, message = "O desconto não pode ser negativo!")
    @Max(value = 100,message = "O desconto não pode ultrapassar 100%")
    private int desconto;

    private double precoDesconto;

    private boolean ativo;

    @ManyToMany(mappedBy = "produtos", fetch = FetchType.LAZY)
    private Set<Pedido> pedidos;

    @NotNull(message = "Selecione uma categoria!")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    (
        name = "idCategoria", nullable = false
    )
    private Categoria idCategoria;

    public int getDesconto()
    {
        return desconto;
    }

    public void setDesconto(int desconto)
    {
        this.desconto = desconto;
    }

    public int getIdProduto() 
    {
        return idProduto;
    }

    public void setIdProduto(int idProduto) 
    {
        this.idProduto = idProduto;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public double getPreco() 
    {
        return preco;
    }

    public void setPreco(double preco) 
    {
        this.preco = preco;
    }

    public String getFabricante() 
    {
        return fabricante;
    }

    public void setFabricante(String fabricante) 
    {
        this.fabricante = fabricante;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPrecoDesconto() {
        return precoDesconto;
    }

    public void setPrecoDesconto(double precoDesconto) {
        this.precoDesconto = precoDesconto;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) 
    {
        this.pedidos = pedidos;
    }

    public Object getIdCategoria() {
        return null;
    }

    public void setIdCategoria(Object object) {
    }
    
}
