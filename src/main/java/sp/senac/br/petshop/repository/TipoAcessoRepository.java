package sp.senac.br.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senac.br.petshop.model.tipoAcesso;

public interface TipoAcessoRepository extends JpaRepository<tipoAcesso, Integer>{
    
}
