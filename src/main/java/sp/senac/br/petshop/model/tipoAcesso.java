package sp.senac.br.petshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tipoAcesso")
public class tipoAcesso{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtipoAcesso")
    private int idTipoAcesso;

    @NotBlank(message = "O campo 'Tipo de Acesso' é obrigatório.")
    private String nome;

    public int getIdTipoAcesso() {
        return idTipoAcesso;
    }

    public void setIdTipoAcesso(int idTipoAcesso) {
        this.idTipoAcesso = idTipoAcesso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public tipoAcesso() {
    }

}
