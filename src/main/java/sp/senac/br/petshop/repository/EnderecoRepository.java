package sp.senac.br.petshop.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import sp.senac.br.petshop.model.Endereco;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer>{
    
}
