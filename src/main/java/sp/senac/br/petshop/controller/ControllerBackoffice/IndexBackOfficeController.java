package sp.senac.br.petshop.controller.ControllerBackoffice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Funcionario;
import sp.senac.br.petshop.repository.FuncionarioRepository;
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
    private FuncionarioRepository funcionarioRepository;

    @GetMapping 
    public ModelAndView index()
    {
        ModelAndView mv = new ModelAndView("/IndexBackoffice");

        List<Funcionario> funcionario = funcionarioRepository.findAll();

        mv.addObject("funcioanrio", funcionario);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView listarFuncionario()
    {
        ModelAndView mv = new ModelAndView("Adicionar");

        mv.addObject("cliente", new Funcionario());

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView adicionar(@ModelAttribute("cliente") @Valid Funcionario f, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/IndexBackoffice");

            f.setAtivo(true);

            funcionarioRepository.save(f);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView alterarCliente(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("Adicionar");

        Funcionario f = funcionarioRepository.getById(id);

        mv.addObject("funcionario", f);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView alterarCliente(@PathVariable int id,
                                @ModelAttribute("funcionario") @Valid Funcionario f,
                                BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/IndexBackoffice");

            Funcionario fAux = funcionarioRepository.getById(id);


            fAux.setCPF(f.getCPF());
            fAux.setEmail(f.getEmail());
            fAux.setNome(f.getNome());
            fAux.setSobrenome(f.getSobrenome());
            fAux.setHashSenha(f.getHashSenha());
            

            funcionarioRepository.save(fAux);

            return mv;
        }
    }

    @PostMapping("Excluir/{id}")
    public ModelAndView excluirCliente(@PathVariable int id)
    {
        Funcionario f = funcionarioRepository.getById(id);

        if(f == null)
        {
            return new ModelAndView("redirect:/IndexbackOffice");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/IndexbackOffice");

            f.setAtivo(false);

            funcionarioRepository.save(f);

            return mv;
        }
    }
    
}
