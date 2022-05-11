package sp.senac.br.petshop.controller.ControllerBackoffice;

import java.util.List;
import java.util.Optional;

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
import sp.senac.br.petshop.model.LinkPermissoes;
import sp.senac.br.petshop.model.Usuario;
import sp.senac.br.petshop.model.tipoAcesso;
import sp.senac.br.petshop.repository.FuncionarioRepository;
import sp.senac.br.petshop.repository.LinkPermissaoRepository;
import sp.senac.br.petshop.repository.TipoAcessoRepository;
import sp.senac.br.petshop.repository.UsuarioRepository;

@RestController
@RequestMapping("/Indexbackoffice/Permissoes")
public class LinkPermissaoController {
    
    @Autowired
    LinkPermissaoRepository linkPermissaoRepository;
    @Autowired   
    FuncionarioRepository funcionarioRepository;
    @Autowired
    TipoAcessoRepository tipoAcessoRepository;

    @GetMapping
    public ModelAndView Listar()
    {
        ModelAndView mv = new ModelAndView("/Permissoes/Listar");

        List<LinkPermissoes> permissoes = linkPermissaoRepository.findAll();

        mv.addObject("permissoes", permissoes);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView Adicionar(LinkPermissoes permissoes)
    {
        ModelAndView mv = new ModelAndView("/Permissoes/Adicionar");

        List<Funcionario> funcionario = funcionarioRepository.findAll();
        List<tipoAcesso> tipoAcessos = tipoAcessoRepository.findAll();

        mv.addObject("funcionario", funcionario);
        mv.addObject("tipoAcessos", tipoAcessos);
        mv.addObject("permissoes", permissoes);
        
        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView Adicionar(@ModelAttribute("permissoes") @Valid LinkPermissoes l, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return Adicionar(l);
        }
        else
        {
             ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/Permissoes");
            linkPermissaoRepository.saveAndFlush(l);         
            return mv;
        }
    }

    // @GetMapping("/Alterar/{id}")
    // public ModelAndView Alterar(@PathVariable int id)
    // {
    //     LinkPermissoes l = linkPermissaoRepository.findById(id).get();

    //     if(l == null)
    //     {
    //         return new ModelAndView("redirect:/Indexbackoffice/Permissoes");
    //     }
    
    //     ModelAndView mv = new ModelAndView("/Permissoes/Adicionar");

    //     mv.addObject("LinkPermissoes", l);

    //     return mv;
    // }

    @GetMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id, @ModelAttribute("permissoes") @Valid LinkPermissoes t){
        Optional <LinkPermissoes> permissoes = linkPermissaoRepository.findById(id);
        return Adicionar(permissoes.get());
    }

    @GetMapping("/Excluir/{id}")
    public ModelAndView Excluir(@PathVariable int id)
    {
        LinkPermissoes l = linkPermissaoRepository.findById(id).get();

        if(l == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/Permissoes");
        }


        linkPermissaoRepository.delete(l);

        return new ModelAndView("redirect:/Indexbackoffice/Permissoes");
    }
}
