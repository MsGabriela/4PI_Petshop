package sp.senac.br.petshop.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.ProdutoRepository;

@RestController
@RequestMapping()
public class IndexController 
{
    @Autowired
    private ProdutoRepository produtoRepository;


    @GetMapping
    public ModelAndView index(Authentication authentication){
        Cliente c = null;

        if(authentication != null){
            c = (Cliente)authentication.getPrincipal();
        }

        List<Produto> produtos = produtoRepository.buscaProdutosAtivos();

        ModelAndView mv = new ModelAndView("index").addObject("usuario", c).addObject("produtos", produtos);

        return mv;
    }

    @GetMapping("/sobrenos")
    public ModelAndView sobrenos(){      

        ModelAndView mv = new ModelAndView("sobre_nos");

        return mv;
    }
}