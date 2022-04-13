package sp.senac.br.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sp.senac.br.petshop.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>
{
    @Query("SELECT u FROM Cliente u WHERE u.ativo = true")
    List<Cliente> buscaUsuariosAtivos();
}
