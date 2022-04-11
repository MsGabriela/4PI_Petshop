package sp.senac.br.petshop.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name = "Clientes")
public class Cliente extends Usuario 
{
   

    public Cliente()
    {

    }

   

}
