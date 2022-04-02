package sp.senac.br.petshop.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Enderecos")
public class Endereco{
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @NotBlank(message = "Preenchar o Logradouro!")
    @Size(max = 100)
    private String logradouro;

    @NotBlank(message = "Preenchar o CEP!")
    @Size(max = 8)
    private String CEP;

    @NotBlank(message = "Preenchar o Número!")
    @Size(max = 5)
    private String numero;

    @NotBlank(message = "Preenchar o Cidade!")
    @Size(max = 100)
    private String cidade;

    @NotBlank(message = "Preenchar o Estado!")
    @Size(max = 2)
    private String UF;

    @ManyToOne
    @JoinColumn(name = "cliente.id", nullable = false)
    private Cliente cliente;

    public Endereco() {
    }

    public Endereco(@NotBlank(message = "Preenchar o Logradouro!") @Size(max = 100) String logradouro,
            @NotBlank(message = "Preenchar o CEP!") @Size(max = 8) String cEP,
            @NotBlank(message = "Preenchar o Número!") @Size(max = 5) String numero,
            @NotBlank(message = "Preenchar o Cidade!") @Size(max = 100) String cidade,
            @NotBlank(message = "Preenchar o Estado!") @Size(max = 2) String uF, Cliente cliente) {
        this.logradouro = logradouro;
        CEP = cEP;
        this.numero = numero;
        this.cidade = cidade;
        UF = uF;
        this.cliente = cliente;
    }
}
