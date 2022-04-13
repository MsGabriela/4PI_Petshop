package sp.senac.br.petshop.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "Clientes")
public class Cliente extends Usuario {
    @OneToMany(mappedBy = "cliente")
    private Set<Endereco> enderecos;

    public Cliente()
    {

    }

    public Cliente(Endereco endereco) 
    {
        super();
        enderecos.add(endereco);
    }

    public Set<Endereco> getEnderecos() 
    {
        return enderecos;
    }

    public void setEnderecos(Set<Endereco> enderecos) 
    {
        this.enderecos = enderecos;
    }

    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() 
    // {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public String getPassword() 
    // {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public String getUsername() 
    // {
    //     // TODO Auto-generated method stub
    //     return null;
    // }

    // @Override
    // public boolean isAccountNonExpired() 
    // {
    //     // TODO Auto-generated method stub
    //     return false;
    // }

    // @Override
    // public boolean isAccountNonLocked() 
    // {
    //     // TODO Auto-generated method stub
    //     return false;
    // }

    // @Override
    // public boolean isCredentialsNonExpired() 
    // {
    //     // TODO Auto-generated method stub
    //     return false;
    // }

    // @Override
    // public boolean isEnabled() 
    // {
    //     // TODO Auto-generated method stub
    //     return false;
    // }

}
