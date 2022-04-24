package sp.senac.br.petshop.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.model.Endereco;
import sp.senac.br.petshop.model.Usuario;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.EnderecoRepository;


import javax.validation.Valid;




@RestController
@RequestMapping("/login")
public class UsuarioController 
{
    
    
        @Autowired
        private ClienteRepository clienteRepository;

        
        @Autowired
        private EnderecoRepository enderecoRepository;

        @GetMapping
        public ModelAndView login(Authentication authentication)
        {
            if(authentication != null)
            {
                Cliente c = (Cliente)authentication.getCredentials();
    
                c.setEnderecos(enderecoRepository.buscaEnderecos(c));
    
                ModelAndView mv = new ModelAndView("redirect:/login/IndexBackOffice");
                mv.addObject("usuario", c);
    
                return mv;
            }
    
            return new ModelAndView("IndexBackOffice");
        }

        @GetMapping("/Cadastrar")
        public ModelAndView Cadastrar()
        {
            ModelAndView mv = new ModelAndView("Cadastrar");
            mv.addObject("usuario", new Cliente());
            return mv;
        }

        @PostMapping("/Cadastrar")
         public ModelAndView Cadastrar(
         @ModelAttribute("usuario")  @Valid Cliente c,
         BindingResult bindingResult)
        { 
            //Concluir o cadastro

            if(bindingResult.hasErrors())
            {
                return new ModelAndView("Cadastrar");
            }
            else 
            {
                ModelAndView mv = new ModelAndView("redirect:/login");
                c.setTipoAcesso(1);
                c.setAtivo(true);
                c.setNome(c.getNome() + " " + c.getSobrenome());
                c.setSenha(c.getHashSenha());
                clienteRepository.save(c); 

                return mv;
            }
        }

    @GetMapping("/Alterar/{id}")
    public ModelAndView alterar(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("Cadastro");

        Cliente c = clienteRepository.getById(id);

        int espaco = c.getNome().indexOf(" ");

        c.setSobrenome(c.getNome().substring(espaco + 1));
        c.setNome(c.getNome().substring(0, espaco));

        mv.addObject("usuario", c);
        return mv;
    }


    @PostMapping("/Alterar/{id}")
    public ModelAndView alterar(@PathVariable int id,
                                @ModelAttribute("usuario")  @Valid Cliente c,
                                BindingResult bindingResult, Authentication authentication)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Cadastro");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/login");

            Cliente cliente = clienteRepository.getById(id);
            cliente.setNome( c.getNome() + " " + c.getSobrenome());
            cliente.setSobrenome(c.getSobrenome());
            cliente.setSenha(c.getHashSenha());
            cliente.setCPF(c.getCPF());
            cliente.setEmail(c.getEmail());
            cliente.setDataNascimento(c.getDataNascimento());
            cliente.setSexo(c.getSexo());
            cliente.setTelefone(c.getTelefone());

            clienteRepository.save(cliente);

            authentication.setAuthenticated(false);

            return mv;
        }
    }

   
   

    @GetMapping("/endereco")
    public ModelAndView endereco()
    {
        ModelAndView mv = new ModelAndView("alterarEndereco");
        mv.addObject("endereco", new Endereco());
        return mv;
    }

    @PostMapping("/endereco")
    public ModelAndView endereco(
            @ModelAttribute("endereco") @Valid Endereco e,
            BindingResult bindingResult, Authentication authentication)
            
    {

        if(bindingResult.hasErrors())
        {
            return new ModelAndView("alterarEndereco");
        }
        else if(authentication != null)
        {
            Usuario u = (Usuario) authentication.getPrincipal();
            e.setUsuario(u);

            enderecoRepository.save(e);

            return new ModelAndView("redirect:/login");
        }

        return new ModelAndView("redirect:/login");
    }
}
