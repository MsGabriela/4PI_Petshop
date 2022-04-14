package sp.senac.br.petshop.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.model.Usuario;
import sp.senac.br.petshop.repository.ProdutoRepository;



@RestController
@RequestMapping("/index")
public class IndexController {
    
    @Autowired
    private ProdutoRepository produtoRepository;
    
    @GetMapping
    public ModelAndView index(Authentication authentication){
        Usuario u = null;

        if(authentication != null){
            u = (Usuario)authentication.getPrincipal();
        }
        
        List<Produto> produtos = produtoRepository.buscaProdutosAtivos();

        ModelAndView mv = new ModelAndView("index").addObject("usuario", u).addObject("produtos", produtos);

        return mv;
    }
    
    @GetMapping("/sobrenos")
    public ModelAndView sobrenos(){      

        ModelAndView mv = new ModelAndView("sobre_nos");

        return mv;
    }
    
}