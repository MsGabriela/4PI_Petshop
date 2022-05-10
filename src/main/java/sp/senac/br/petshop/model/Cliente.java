package sp.senac.br.petshop.model;

import java.util.Collection;
import java.util.Set;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;

//import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "Clientes")
public class Cliente extends Usuario {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date dataNascimento;

    @NotBlank(message = "Preenchar o telefone!")
    private String telefone;
    private String sexo;

    @OneToMany(mappedBy = "cliente")
    private Set<Endereco> enderecos;

    public Cliente() {

    }

    public Cliente(Endereco endereco) {
        super();
        enderecos.add(endereco);
    }

    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

}
