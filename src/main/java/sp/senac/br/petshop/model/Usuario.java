package sp.senac.br.petshop.model;


import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
//import sp.senac.br.pet.SecurityConfig;
//import sp.senac.br.pet.constraint.FieldMatch;

import sp.senac.br.petshop.Validator.SecurityConfig;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;
import java.util.Set;




@Entity
@Table(name = "usuario")
public  class Usuario implements UserDetails
{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   
    @Column(name = "idusuario")
    private Long idUsuario;

    @NotBlank(message = "Preenchar o nome!")
    @Size(max = 100)
    private String nome;

    @NotBlank(message = "Preenchar o sobrenome!")
    @Size(max = 100)
    private String sobrenome;

    @CPF(message = "CPF inválido!")
    private String CPF;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dataNascimento;

    @NotBlank(message = "Preenchar o telefone!")
    private String telefone;

    
    @NotBlank(message = "Preenchar o Email!")
    @Email(message = "Email inválido!")
    private String email;

    @Transient
    private String ConfirmarEmail;
    
    private boolean ativo;
    private String sexo;
    

    @NotBlank(message = "Preencha a senha!")
    @Column(name = "senha")
    private String hashSenha;

    @Transient
    private String csenha;

    // @OneToMany(mappedBy = "cliente", fetch = FetchType.Lazy)
    // private Set<Pedido> pedidos;

    @Column(name = "tipoacesso")
    private int tipoAcesso;
    
    @OneToMany(mappedBy = "usuario")
    private Set<Endereco> enderecos;

    public Usuario() 
    {
        
    }
    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public String getSexo() 
    {
        return sexo;
    }


    public void setSexo(String sexo) 
    {
        this.sexo = sexo;
    }


    public boolean getAtivo() 
    {
        return ativo;
    }


    public void setAtivo(boolean ativo) 
    {
        this.ativo = ativo;
    }

    public int getTipoAcesso() 
    {
        return tipoAcesso;
    }

    public void setTipoAcesso(int tipoAcesso) 
    {
        this.tipoAcesso = tipoAcesso;
    }

    
    private String senha;
    private String ConfirmarSenha;

    public Long getIdUser() 
    {
        return idUsuario;
    }

    public void setIdUser(Long idUser) 
    {
        this.idUsuario = idUser;
    }

    public String getName() 
    {
        return nome;
    }

    public void setName(String name) 
    {
        this.nome = name;
    }

    public String getSobrenome() 
    {
        return sobrenome;
    }



    public void setSobrenome(String sobrenome) 
    {
        this.sobrenome = sobrenome;
    }


    public String getCPF() 
    {
        return CPF;
    }


    public void setCPF(String cpf) 
    {
        CPF = cpf;
    }


    public Date getDataNascimento() 
    {
        return dataNascimento;
    }


    public void setDataNascimento(Date dataNascimento) 
    {
        this.dataNascimento = dataNascimento;
    }


    public String getTelefone() 
    {
        return telefone;
    }

    
    public void setTelefone(String telefone)
    {
        this.telefone = telefone;
    }

    public String getEmail() 
    {
        return email;
    }


    public void setEmail(String email) 
    {
        this.email = email;
    }


    public String getConfirmarEmail() 
    {
        return ConfirmarEmail;
    }


    public void setConfirmarEmail(String confirmarEmail) 
    {
        this.ConfirmarEmail = confirmarEmail;
    }


    public String getHashSenha()
    {
        return hashSenha;
    }


    public void  setHashSenha(String hashsenha)
    {
        this.hashSenha = hashsenha;
    }


    public String getSenha() 
    {
        return senha;
    }


    public void setSenha(String senha) 
    {
        this.hashSenha =
                SecurityConfig.bcryptPasswordEncoder()
                        .encode(senha);
    }


    public void setCsenha(String csenha) 
    {
        this.csenha = SecurityConfig.bcryptPasswordEncoder()
                .encode(csenha);
    }



    public String getConfirmarSenha() 
    {
        return ConfirmarSenha;
    }

    
    public void setConfirmarSenha(String confirmarSenha) 
    {
        ConfirmarSenha = confirmarSenha;
    }
    
    public Set<Endereco> getEnderecos() 
    {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) 
    {
        this.enderecos = enderecos;
    }
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        return null;
    }

    @Override
    public String getPassword() 
    {
        return getHashSenha();
    }

    @Override
    public String getUsername() 
    {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() 
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() 
    {
        return true;
    }

    @Override
    public boolean isEnabled() 
    {
        return true;
    }
    
}
