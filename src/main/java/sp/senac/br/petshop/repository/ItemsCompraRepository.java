package sp.senac.br.petshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senac.br.petshop.model.ItemsCompra;

public interface ItemsCompraRepository extends JpaRepository<ItemsCompra, Integer>
{
    
}