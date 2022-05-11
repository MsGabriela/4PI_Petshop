package sp.senac.br.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sp.senac.br.petshop.model.LinkPermissoes;

public interface LinkPermissaoRepository extends JpaRepository<LinkPermissoes, Integer>{


    
}
