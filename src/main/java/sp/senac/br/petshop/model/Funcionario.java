package sp.senac.br.petshop.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class Funcionario extends Usuario{

    private boolean Admin;

    public Funcionario(){

    }

    public Funcionario(boolean Admin){
        super();
        this.Admin = Admin;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public void setAdmin(boolean Admin) {
        this.Admin = Admin;
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
