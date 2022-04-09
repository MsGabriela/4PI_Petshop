package sp.senac.br.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senac.br.petshop.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>
{
    
}
