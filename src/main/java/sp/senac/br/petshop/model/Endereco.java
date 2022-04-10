package sp.senac.br.petshop.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Enderecos")
public class Endereco
{
    
    @Id
    @Column(name = "idendereco")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotBlank(message = "Preencha a rua!")
    @Size(max = 200)
    private String rua;

    @NotBlank(message = "Preencha o número!")
    @Size(max = 11)
    private String numero;

    @NotBlank(message = "Preencha o CEP!")
    @Size(max = 11)
    private String cep;

    @NotBlank(message = "Preencha o bairro!")
    @Size(max = 45)
    private String bairro;

    private String complemento;

    @NotBlank(message = "Preencha o apelido!")
    @Size(max = 20)
    private String apelido;

    @NotBlank(message = "Preencha a cidade!")
    @Size(max = 40)
    private String cidade;

    @NotBlank(message = "Preencha o estado!")
    @Size(max = 40)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "cliente.id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idusuario")
    private Usuario usuario;

   

    public String getRua() 
    {
        return rua;
    }



    public void setRua(String rua) 
    {
        this.rua = rua;
    }



    public String getNumero() 
    {
        return numero;
    }



    public void setNumero(String numero) 
    {
        this.numero = numero;
    }



    public String getCep() 
    {
        return cep;
    }



    public void setCep(String cep)
    {
        this.cep = cep;
    }



    public String getBairro() 
    {
        return bairro;
    }



    public void setBairro(String bairro) 
    {
        this.bairro = bairro;
    }



    public String getComplemento() 
    {
        return complemento;
    }



    public void setComplemento(String complemento) 
    {
        this.complemento = complemento;
    }



    public String getApelido() 
    {
        return apelido;
    }



    public void setApelido(String apelido) 
    {
        this.apelido = apelido;
    }



    public String getCidade() 
    {
        return cidade;
    }



    public void setCidade(String cidade) 
    {
        this.cidade = cidade;
    }



    public String getEstado() 
    {
        return estado;
    }



    public void setEstado(String estado) 
    {
        this.estado = estado;
    }



    public Cliente getCliente() 
    {
        return cliente;
    }



    public void setCliente(Cliente cliente)
    {
        this.cliente = cliente;
    }



    public Usuario getUsuario() 
    {
        return usuario;
    }



    public void setUsuario(Usuario usuario) 
    {
        this.usuario = usuario;
    }



    public Endereco(UUID id, @NotBlank(message = "Preencha a rua!") @Size(max = 200) String rua,
            @NotBlank(message = "Preencha o número!") @Size(max = 11) String numero,
            @NotBlank(message = "Preencha o CEP!") @Size(max = 11) String cep,
            @NotBlank(message = "Preencha o bairro!") @Size(max = 45) String bairro, String complemento,
            @NotBlank(message = "Preencha o apelido!") @Size(max = 20) String apelido,
            @NotBlank(message = "Preencha a cidade!") @Size(max = 40) String cidade,
            @NotBlank(message = "Preencha o estado!") @Size(max = 40) String estado, Cliente cliente, Usuario usuario) 
            {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.bairro = bairro;
        this.complemento = complemento;
        this.apelido = apelido;
        this.cidade = cidade;
        this.estado = estado;
        this.cliente = cliente;
        this.usuario = usuario;
    }



    public Endereco() 
    {
        
    }



    
}
