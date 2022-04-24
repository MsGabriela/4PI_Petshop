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
import sp.senac.br.petshop.repository.CategoriaRepository;

@RestController
@RequestMapping("/Indexbackoffice/Categorias")
public class CategoriasController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ModelAndView Listar()
    {
        ModelAndView mv = new ModelAndView("/Categorias/Listar");

        List<Categoria> categorias = categoriaRepository.findAll();

        mv.addObject("categorias", categorias);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView Adicionar()
    {
        ModelAndView mv = new ModelAndView("/Categorias/Adicionar");

        mv.addObject("categoria", new Categoria());

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView Adicionar(@ModelAttribute("categoria") @Valid Categoria c, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("/Categorias/Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/Categorias");

            c.setAtivo(true);

            categoriaRepository.save(c);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id)
    {
        Categoria c = categoriaRepository.findById(id).get();

        if(c == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/Categorias");
        }
    
        ModelAndView mv = new ModelAndView("/Categorias/Adicionar");

        mv.addObject("categoria", c);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id, @ModelAttribute("categoria") @Valid Categoria c, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/Categorias");

            Categoria cAux = categoriaRepository.findById(id).get();

            if(cAux == null)
            {
                return new ModelAndView("redirect:/Indexbackoffice/Categorias");
            }

            cAux.setNome(c.getNome());

            categoriaRepository.save(cAux);

            return mv;
        }
    }

    @GetMapping("/Excluir/{id}")
    public ModelAndView Excluir(@PathVariable int id)
    {
        Categoria c = categoriaRepository.findById(id).get();

        if(c == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/Categorias");
        }

        if(c.getAtivo())
        {
            c.setAtivo(false); 
        }
        else
        {
            c.setAtivo(true);
        }

        categoriaRepository.save(c);

        return new ModelAndView("redirect:/Indexbackoffice/Categorias");
    }
}
