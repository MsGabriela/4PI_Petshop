package sp.senac.br.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sp.senac.br.petshop.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> 
{

    @Query("SELECT u FROM Usuario u WHERE u.ativo = 1")
    List<Funcionario> buscaUsuariosAtivos();
}

