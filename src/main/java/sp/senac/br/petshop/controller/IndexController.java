// package sp.senac.br.petshop.controller;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import sp.senac.br.petshop.repository.ProdutoRepository;

// @RestController
// @RequestMapping("/index")
// public class IndexController 
// {
//     @Autowired
//     private ProdutoRepository produtoRepository;

     
//     @GetMapping
//     public ModelAndView index(Authentication authentication){
//         Usuario u = null;

//         if(authentication != null){
//             u = (Usuario)authentication.getPrincipal();
//         }
        
//         List<Produto> produtos = produtoRepository.buscaProdutosAtivos();

//         ModelAndView mv = new ModelAndView("index").addObject("usuario", u).addObject("produtos", produtos);

//         return mv;
//     }
    
//     @GetMapping("/sobrenos")
//     public ModelAndView sobrenos(){      

//         ModelAndView mv = new ModelAndView("sobre_nos");

//         return mv;
//     }
// }
