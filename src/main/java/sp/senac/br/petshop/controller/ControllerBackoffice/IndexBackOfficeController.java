package sp.senac.br.petshop.controller.ControllerBackoffice;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.model.Produto;
//import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.ProdutoRepository;
import sp.senac.br.petshop.repository.UsuarioRepository;

@RestController
@RequestMapping("/Indexbackoffice")
public class IndexBackOfficeController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping 
    public ModelAndView index()
    {
        ModelAndView mv = new ModelAndView("/IndexBackoffice");

        List<Cliente> clientes = clienteRepository.findAll();

        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView listarClientes()
    {
        ModelAndView mv = new ModelAndView("Adicionar");

        mv.addObject("cliente", new Cliente());

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView adicionar(@ModelAttribute("cliente") @Valid Cliente c, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice");

            c.setAtivo(true);

            clienteRepository.save(c);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView alterarCliente(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("Adicionar");

        Cliente c = clienteRepository.getById(id);

        mv.addObject("cliente", c);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView alterarCliente(@PathVariable int id,
                                @ModelAttribute("cliente") @Valid Cliente c,
                                BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice");

            Cliente cAux = clienteRepository.getById(id);

            cAux.setAtivo(c.getAtivo());
            cAux.setCPF(c.getCPF());
            cAux.setDataNascimento(c.getDataNascimento());
            cAux.setEmail(c.getEmail());
            cAux.setConfirmarEmail(c.getConfirmarEmail());
            cAux.setName(c.getName());
            cAux.setSexo(c.getSexo());
            cAux.setSobrenome(c.getSobrenome());
            cAux.setTelefone(c.getTelefone());
            cAux.setHashSenha(c.getHashSenha());
            cAux.setConfirmarSenha(c.getConfirmarSenha());
            

            clienteRepository.save(cAux);

            return mv;
        }
    }

    @PostMapping("Excluir/{id}")
    public ModelAndView excluirCliente(@PathVariable int id)
    {
        Cliente c = clienteRepository.getById(id);

        if(c == null)
        {
            return new ModelAndView("redirect:/IndexbackOffice");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/IndexbackOffice");

            c.setAtivo(false);

            clienteRepository.save(c);

            return mv;
        }
    }
    
}
