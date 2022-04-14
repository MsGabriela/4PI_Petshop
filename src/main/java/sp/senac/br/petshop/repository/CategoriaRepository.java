package sp.senac.br.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import sp.senac.br.petshop.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

}
