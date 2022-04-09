// package sp.senac.br.petshop.repository;

// import java.util.Set;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import sp.senac.br.petshop.model.Pedido;
// import sp.senac.br.petshop.model.Usuario;

// public interface PedidoRepository extends JpaRepository<Pedido, Integer>
// {
//     @Query("SELECT p FROM Pedido p WHERE p.cliente = :u")
//     Set<Pedido> buscaPedidosUsuario(@Param("u") Usuario u);
// }
