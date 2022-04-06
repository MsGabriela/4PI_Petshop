package sp.senac.br.petshop.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

@MappedSuperclass
public abstract class Usuario{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long idUser;

    @NotBlank(message = "Preenchar o nome!")
    @Size(max = 100)
    private String name;

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

    private String ConfirmarEmail;
    
    private boolean ativo;
    private String sexo;

    
    public String getSexo() {
        return sexo;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
    private String senha;
    private String ConfirmarSenha;

    public Long getIdUser() {
        return idUser;
    }
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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
    public String getConfirmarEmail() {
        return ConfirmarEmail;
    }
    public void setConfirmarEmail(String confirmarEmail) {
        ConfirmarEmail = confirmarEmail;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getConfirmarSenha() {
        return ConfirmarSenha;
    }
    public void setConfirmarSenha(String confirmarSenha) {
        ConfirmarSenha = confirmarSenha;
    }

    
}
