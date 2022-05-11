package sp.senac.br.petshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "permissoes")
public class LinkPermissoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int idPermissao;

    @ManyToOne
    private Funcionario funcionario;

    @ManyToOne
    private tipoAcesso tipoAcessos;
    
}
