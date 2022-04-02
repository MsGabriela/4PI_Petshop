package sp.senac.br.petshop.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.br.CPF;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Clientes")
public class Cliente{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Preenchar o nome!")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "Preenchar o sobrenome!")
    @Size(max = 100)
    private String sobrenome;

    @CPF(message = "CPF inválido!")
    private String CPF;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @PastOrPresent
    private Date dataNascimento;

    @NotBlank(message = "Preenchar o telefone!")
    private String telefone;

    @Email(message = "Email inválido!")
    @NotBlank(message = "Preenchar o Email!")
    private String email;
    private String sexo;
    private boolean ativo;

    @OneToMany(mappedBy = "cliente")
    private Set<Endereco> enderecos;

    public Cliente(String nome, 
                   String sobrenome, 
                   Date dataNascimento, 
                   String telefone, 
                   String email, 
                   String sexo,
                   boolean ativo,
                   String CPF,
                   Set<Endereco> enderecos) {

        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.email = email;
        this.sexo = sexo;
        this.ativo = ativo;
        this.CPF = CPF;
        this.enderecos = enderecos;
    }

    public Cliente(){
        enderecos = new HashSet<Endereco>();
    }

    public int getId()
    {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCPF()
    {
        return this.CPF;
    }

    public void setCPF(String CPF)
    {
        this.CPF = CPF;
    }
}
