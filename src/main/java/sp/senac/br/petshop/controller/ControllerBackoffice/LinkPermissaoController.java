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

import sp.senac.br.petshop.model.Categoria;
import sp.senac.br.petshop.model.LinkPermissoes;
import sp.senac.br.petshop.model.Usuario;
import sp.senac.br.petshop.model.tipoAcesso;
import sp.senac.br.petshop.repository.CategoriaRepository;
import sp.senac.br.petshop.repository.LinkPermissaoRepository;
import sp.senac.br.petshop.repository.TipoAcessoRepository;
import sp.senac.br.petshop.repository.UsuarioRepository;

@RestController
@RequestMapping("/Indexbackoffice/LinkPermissao")
public class LinkPermissaoController {
    
    @Autowired
    LinkPermissaoRepository linkPermissaoRepository;
    @Autowired   
    UsuarioRepository usuarioRepository;
    @Autowired
    TipoAcessoRepository tipoAcessoRepository;

    @GetMapping
    public ModelAndView Listar()
    {
        ModelAndView mv = new ModelAndView("/TipoAcessos/Listar");

        List<LinkPermissoes> linkPermissao = linkPermissaoRepository.findAll();

        mv.addObject("linkPermissao", linkPermissao);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView Adicionar(LinkPermissoes permissoes)
    {
        ModelAndView mv = new ModelAndView("/LinkPermissoes/Adicionar");

        List<Usuario> usuarios = usuarioRepository.findAll();
        List<tipoAcesso> tipoAcessos = tipoAcessoRepository.findAll();

        mv.addObject("usuarios", usuarios);
        mv.addObject("tipoAcessos", tipoAcessos);
        mv.addObject("permissoes", permissoes);

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView Adicionar(@ModelAttribute("LinkPermissoes") @Valid LinkPermissoes l, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return Adicionar(l);
        }
        else
        {
            linkPermissaoRepository.saveAndFlush(l);

            return Adicionar(new LinkPermissoes());
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id)
    {
        LinkPermissoes l = linkPermissaoRepository.findById(id).get();

        if(l == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/LinkPermissoes");
        }
    
        ModelAndView mv = new ModelAndView("/LinkPermissoes/Adicionar");

        mv.addObject("LinkPermissoes", l);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id, @ModelAttribute("LinkPermissoes") @Valid LinkPermissoes t){
        Optional <LinkPermissoes> idPer = linkPermissaoRepository.findById(id);
        return Adicionar(idPer.get());
    }

    @GetMapping("/Excluir/{id}")
    public ModelAndView Excluir(@PathVariable int id)
    {
        LinkPermissoes l = linkPermissaoRepository.findById(id).get();

        if(l == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/LinkPermissoes");
        }


        linkPermissaoRepository.delete(l);

        return new ModelAndView("redirect:/Indexbackoffice/LinkPermissoes");
    }
}
