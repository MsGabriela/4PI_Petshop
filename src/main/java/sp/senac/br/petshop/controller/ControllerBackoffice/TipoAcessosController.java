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

import sp.senac.br.petshop.model.Categoria;
import sp.senac.br.petshop.model.tipoAcesso;
import sp.senac.br.petshop.repository.CategoriaRepository;
import sp.senac.br.petshop.repository.TipoAcessoRepository;

@RestController
@RequestMapping("/Indexbackoffice/TipoAcessos")
public class TipoAcessosController {
    
    @Autowired
    private TipoAcessoRepository tipoAcessoRepository;

    @GetMapping
    public ModelAndView Listar()
    {
        ModelAndView mv = new ModelAndView("/TipoAcessos/Listar");

        List<tipoAcesso> tiposAcessos = tipoAcessoRepository.findAll();

        mv.addObject("tipoAcessos", tiposAcessos);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView Adicionar()
    {
        ModelAndView mv = new ModelAndView("/TipoAcessos/Adicionar");

        mv.addObject("tipoAcesso", new tipoAcesso());

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView Adicionar(@ModelAttribute("tipoAcesso") @Valid tipoAcesso t, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("/TipoAcessos/Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/TipoAcessos");
            tipoAcessoRepository.save(t);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id)
    {
        tipoAcesso t = tipoAcessoRepository.findById(id).get();

        if(t == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/TipoAcessos");
        }
    
        ModelAndView mv = new ModelAndView("/TipoAcessos/Adicionar");

        mv.addObject("tipoAcesso", t);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id, @ModelAttribute("tipoAcesso") @Valid tipoAcesso t, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/TipoAcessos");

            tipoAcesso tAux = tipoAcessoRepository.findById(id).get();

            if(tAux == null)
            {
                return new ModelAndView("redirect:/Indexbackoffice/TipoAcessos");
            }

            tAux.setNome(t.getNome());

            tipoAcessoRepository.save(tAux);

            return mv;
        }
    }

    @GetMapping("/Excluir/{id}")
    public ModelAndView Excluir(@PathVariable int id)
    {
        tipoAcesso t = tipoAcessoRepository.findById(id).get();

        if(t == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/TipoAcessos");
        }


        tipoAcessoRepository.delete(t);

        return new ModelAndView("redirect:/Indexbackoffice/TipoAcessos");
    }
}
