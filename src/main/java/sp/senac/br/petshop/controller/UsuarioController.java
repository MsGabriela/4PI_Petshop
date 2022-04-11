package sp.senac.br.petshop.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.model.Endereco;
import sp.senac.br.petshop.model.Pedido;
import sp.senac.br.petshop.model.Usuario;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.EnderecoRepository;
import sp.senac.br.petshop.repository.PedidoRepository;

import javax.validation.Valid;




@RestController
@RequestMapping("/login")
public class UsuarioController 
{
    
    
        @Autowired
        private ClienteRepository clienteRepository;

        @Autowired
        private PedidoRepository  pedidoRepository;

        @Autowired
        private EnderecoRepository enderecoRepository;

        @GetMapping
        public ModelAndView login(Authentication authentication)
        {
            if(authentication != null)
            {
                Cliente c = (Cliente)authentication.getPrincipal();
        
                c.setEnderecos(enderecoRepository.buscaEnderecos(c));

                ModelAndView mv = new ModelAndView("redirect:/login/minhaconta");
                mv.addObject("usuario", c);

                return mv;
            }

            return new ModelAndView("/login");
                
        }

        @GetMapping("/Cadastro")
        public ModelAndView cadastrar()
        {
            ModelAndView mv = new ModelAndView("Cadastro");
            mv.addObject("usuario", new Cliente());
            return mv;
        }

        @PostMapping("/Cadastro")
         public ModelAndView cadastrar(
         @ModelAttribute("usuario")  @Valid Cliente c,
         BindingResult bindingResult)
        { 
            //Concluir o cadastro

            if(bindingResult.hasErrors())
            {
                return new ModelAndView("Cadastro");
            }
            else 
            {
                ModelAndView mv = new ModelAndView("redirect:/index");
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

        Cliente c = clienteRepository.getOne(id);

        int espaco = c.getNome().indexOf(" ");

        c.setSobrenome(c.getNome().substring(espaco + 1));
        c.setNome(c.getNome().substring(0, espaco));

        mv.addObject("usuario", c);
        return mv;
    }


    @PostMapping("/Alterar/{id}")
    public ModelAndView alterar(@PathVariable int id,
                                @ModelAttribute("usuario")  @Valid Usuario u,
                                BindingResult bindingResult, Authentication authentication)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("cadastro");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/login");

           Cliente c = clienteRepository.getOne(id);
            c.setNome(c.getNome() + " " + c.getSobrenome());
            c.setSobrenome(c.getSobrenome());
            c.setCPF(c.getCPF());
            c.setEmail(c.getEmail());
            c.setSenha(c.getHashSenha());
            c.setDataNascimento(c.getDataNascimento());
            c.setSexo(c.getSexo());
            c.setTelefone(c.getTelefone());

            clienteRepository.save(c);

            authentication.setAuthenticated(false);

            return mv;
        }
    }

    @GetMapping("/minhaconta")
    public ModelAndView minhaconta(Authentication authentication) 
    { 
        //Mostrar o formulário de cadastro
        
        if(authentication != null)
        {
            Usuario u = (Usuario)authentication.getPrincipal();
            u.setEnderecos(enderecoRepository.buscaEnderecos(u));
            Set<Pedido> pedidos = pedidoRepository.buscaPedidosUsuario(u);
            ModelAndView mv = new ModelAndView("minhaconta").addObject("pedidos", pedidos).addObject("usuario", u);
            return mv;
        }        
        return new ModelAndView("login");
    }

    @GetMapping("/detalhespedido/{id}")
    public ModelAndView pedidos(@PathVariable int id, Authentication authentication)
     { 
         //Mostrar o formulário de cadastro


        if(authentication != null)
        {
            Usuario u = (Usuario)authentication.getPrincipal();
            u.setEnderecos(enderecoRepository.buscaEnderecos(u));
            Pedido pedido = pedidoRepository.getOne(id);
            ModelAndView mv = new ModelAndView("detalhepedido").addObject("pedido", pedido).addObject("usuario", u);
            return mv;
        }        
        return new ModelAndView("login");
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
