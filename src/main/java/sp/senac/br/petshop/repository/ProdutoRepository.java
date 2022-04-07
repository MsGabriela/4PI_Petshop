package sp.senac.br.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sp.senac.br.petshop.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer>
{
    @Query("SELECT p FROM Produto p WHERE p.ativo = 1")
    List<Produto> buscaProdutosAtivos();
    
}
