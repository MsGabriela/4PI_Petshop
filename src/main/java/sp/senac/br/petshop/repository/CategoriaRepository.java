package sp.senac.br.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sp.senac.br.petshop.model.Categoria;


public interface CategoriaRepository extends JpaRepository<Categoria, Integer>
{
    @Query("SELECT c FROM Categoria c WHERE c.ativo = 1")
    List<Categoria> buscaCategoriasAtivas();
}
