package sp.senac.br.petshop.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.model.Endereco;
import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.EnderecoRepository;
import sp.senac.br.petshop.repository.ProdutoRepository;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // @Autowired
    // private CategoriaRepository categoriaRepository;
    
    // @Autowired
    // private PedidoRepository pedidoRepository;
    
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    // @GetMapping()
    // public ModelAndView listarProdutos(){
    //     ModelAndView mv = new ModelAndView("todos-produtos");

    //     List<Produto> produtos = produtoRepository.buscaProdutosAtivos();

    //     //List<Categoria> categorias = categoriaRepository.findAll();

    //     mv.addObject("produtos", produtos);
    //     //mv.addObject("categorias", categorias);

    //     return mv;
    // }
    
    @GetMapping("/checkout")
    public ModelAndView checkout(Authentication authentication){
        if(authentication != null){
            Cliente c = (Cliente)authentication.getPrincipal();

            Set<Endereco> enderecos = enderecoRepository.buscaEnderecos(c);
            ModelAndView mv = new ModelAndView("checkout").addObject("endereco", new Endereco()).addObject("enderecos", enderecos).addObject("usuario", c);

            return mv;
        }
        
        
        return new ModelAndView("redirect:/login");
    }
    
    // @PostMapping("/checkout")
    // public String finalizaCompra(@RequestBody Carrinho carrinhoJSON){
    //     List<Produto> produtos = new ArrayList<>();
        
    //     for(int i = 0; i < carrinhoJSON.getProdutos().size() ; i++)
    //     {
    //         Produto p = produtoRepository.getOne(carrinhoJSON.getProdutos().get(i).getId());
    //         p.setEstoque(p.getEstoque()-1);

    //         if(p.getEstoque() < 0)
    //             p.setEstoque(0);

    //         for(int k = 0; k < carrinhoJSON.getProdutos().get(i).getQuantidade(); k++)
    //         {
    //             produtos.add(p);
    //         }
    //         produtoRepository.save(p);
            
    //     }
        
    //     Pedido novo_pedido = new Pedido();

    //     Usuario u = usuarioRepository.getOne(carrinhoJSON.getCliente());
        
    //     novo_pedido.setIdEndereco(carrinhoJSON.getIdEndereco());
    //     novo_pedido.setCliente(u);
    //     novo_pedido.setData(LocalDateTime.now());
    //     novo_pedido.setIdTipoPagamento(carrinhoJSON.getTipoPagamento());
    //     novo_pedido.setPrecoVenda(BigDecimal.valueOf(carrinhoJSON.getValor()));
    //     novo_pedido.setProdutos(produtos);
        
    //     pedidoRepository.save(novo_pedido);
        
    //     return "ok";
        
    // }

    @GetMapping("/detalhe/{id}")
    public ModelAndView detalhe(@PathVariable int id){
        ModelAndView mv = new ModelAndView("detalheProduto");

        Produto p = produtoRepository.getById(id);

        mv.addObject("produto", p);

        return mv;
    }
    
    @PostMapping("/endereco")
    public ModelAndView endereco(
            @ModelAttribute("endereco") @Valid Endereco e,
            BindingResult bindingResult, Authentication authentication){

        if(bindingResult.hasErrors()){
            return new ModelAndView("alterarEndereco");
        }
        else if(authentication != null){
            Cliente c = (Cliente) authentication.getPrincipal();
            e.setUsuario(c);

            enderecoRepository.save(e);

            return new ModelAndView("redirect:/produto/checkout");
        }

        return new ModelAndView("redirect:/login");
    }

}

