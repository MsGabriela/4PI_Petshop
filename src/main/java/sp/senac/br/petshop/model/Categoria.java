package sp.senac.br.petshop.model;



import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "categoria")
public class Categoria 
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "idCategoria")
    private int idCategoria;
    private String nome;

    @OneToMany(mappedBy = "idCategoria", fetch = FetchType.LAZY)
    private Set<Produto> produtos;

    public Set<Produto> getProdutos() 
    {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) 
    {
        this.produtos = produtos;
    }

    public int getIdCategoria() 
    {
        return idCategoria;
    }

    public void setIdCategoria(int id) 
    {
        this.idCategoria = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

}

