package sp.senac.br.petshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.ProdutoRepository;

@RestController
public class ProdutoController {
    
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping("Index")
    public ModelAndView index(){

        ModelAndView mv = new ModelAndView("Index");

        List<Produto> produtos = produtoRepository.findAll();

        mv.addObject("produtos", produtos);

        return mv;
    }

}
