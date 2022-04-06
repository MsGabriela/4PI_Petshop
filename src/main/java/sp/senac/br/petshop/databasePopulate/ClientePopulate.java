package sp.senac.br.petshop.databasePopulate;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.repository.ClienteRepository;

@Configuration
@Profile("teste")
public class ClientePopulate implements CommandLineRunner{

    @Autowired
    public ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        PopularClientes();
    }
    
    public void PopularClientes()
    {
        Random rand = new Random();

        for(int i = 0; i < 10; i++)
        {
            Cliente c = new Cliente();
            c.setAtivo(rand.nextBoolean());
            c.setDataNascimento(new Date());
            c.setEmail("email@email.com");
            c.setName("Cliente " + 1);
            c.setSobrenome("Teste");
            c.setSexo("Masculino");
            c.setTelefone("65987458");
            c.setCPF("61266859039");
            
            clienteRepository.save(c);
        }
    }
}
