package sp.senac.br.petshop.model;

import java.util.Collection;

import javax.persistence.Entity;

import javax.persistence.Table;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "Funcionario")
public class Funcionario extends Usuario{


    public Funcionario(){

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    
        return null;
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
    public boolean isEnabled() {

        return false;
    } 
}
