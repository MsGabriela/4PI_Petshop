package sp.senac.br.petshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sp.senac.br.petshop.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> 
{

    @Query("SELECT u FROM Usuario u WHERE u.ativo = 1")
    List<Usuario> buscaUsuariosAtivos();
}

