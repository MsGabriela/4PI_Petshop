package sp.senac.br.petshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.senac.br.petshop.repository.ProdutoRepository;

@RestController
@RequestMapping("/index")
public class IndexController 
{
    @Autowired
    private ProdutoRepository produtoRepository;
}
