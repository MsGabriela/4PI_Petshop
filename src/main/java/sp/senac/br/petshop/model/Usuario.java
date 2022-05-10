package sp.senac.br.petshop.model;


import org.hibernate.validator.constraints.br.CPF;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;



//implements UserDetails

@Entity
@Table(name = "usuario")
public abstract class Usuario implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idusuario")
    private int idUsuario;

    @NotBlank(message = "Preenchar o nome!")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "Preenchar o sobrenome!")
    @Size(max = 100)
    private String sobrenome;

    @CPF(message = "CPF inválido!")
    private String CPF;

    @NotBlank(message = "Preenchar o Email!")
    @Email(message = "Email inválido!")
    private String email;

    @ManyToMany
    private List<tipoAcesso> tiposAcessos;

    private boolean ativo;
    


    @NotBlank(message = "Preencha a senha!")
    //@Column(name = "senha")
    private String hashSenha;

    public Usuario()
    {

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String cPF) {
        CPF = cPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getHashSenha() {
        return hashSenha;
    }

    public void setHashSenha(String hashSenha) {
        this.hashSenha = hashSenha;
    }
}