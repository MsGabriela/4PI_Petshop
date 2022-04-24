package sp.senac.br.petshop.controller;

import java.util.ArrayList;
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


import sp.senac.br.petshop.model.Categoria;
import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.CategoriaRepository;
import sp.senac.br.petshop.repository.ProdutoRepository;

@RestController
@RequestMapping("/Indexbackoffice/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    
 
    
    




    @GetMapping()
    public ModelAndView listarProdutos(){
        ModelAndView mv = new ModelAndView("todos-produtos");

        List<Produto> produtos = produtoRepository.buscaProdutosAtivos();

        List<Categoria> categorias = categoriaRepository.findAll();

        mv.addObject("produtos", produtos);
        mv.addObject("categorias", categorias);

        return mv;
    }
    
    // @GetMapping("/checkout")
    // public ModelAndView checkout(Authentication authentication){
    //     if(authentication != null){
    //         Cliente c = (Cliente)authentication.getPrincipal();

    //         Set<Endereco> enderecos = enderecoRepository.buscaEnderecos(c);
    //         ModelAndView mv = new ModelAndView("checkout").addObject("endereco", new Endereco()).addObject("enderecos", enderecos).addObject("usuario", c);

    //         return mv;
    //     }
        
        
    //     return new ModelAndView("redirect:/login");
    // }
    
    // @PostMapping("/checkout")
    // public String finalizaCompra(@RequestBody Carrinho carrinhoJSON){
    //     List<Produto> produtos = new ArrayList<>();
        
    //     for(int i = 0; i < carrinhoJSON.getProdutos().size() ; i++)
    //     {
    //         Produto p = produtoRepository.find One(carrinhoJSON.getProdutos().get(i).getId());
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

    @GetMapping("/Indexbackoffice/detalhe/{id}")
    public ModelAndView detalhe(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("detalheProduto");

        Produto p = produtoRepository.getById(id);

        mv.addObject("produto", p);

        return mv;
    }


  

    @GetMapping("/cadastroProduto")
    public ModelAndView addProduto(){

        ModelAndView mv = new ModelAndView("cadastroProduto");

        mv.addObject("produto", new Produto());

        return mv;
    }

    // @PostMapping("/cadastroProduto")
    // public ModelAndView salvarProduto(@RequestParam("fileProduto") MultipartFile file,
    // @ModelAttribute("produto") @Valid Produto p, BindingResult bindingResult){
    //     if(bindingResult.hasErrors())
    //     {
    //         return new ModelAndView("cadastroProduto");
    //     }
    //     else
    //     {
    //         ModelAndView mv = new ModelAndView("redirect:/IndexbackOffice");
    //                 try{
    //                  p.setImagem(file.getBytes());   
    //                 }catch(IOException e){
    //                     e.printStackTrace();
    //                 }

    //         p.setAtivo(true);

    //         produtoRepository.save(p);

    //         return mv;
    //     }
    // }

    // @GetMapping("/imagem/{idProduto}")
    // @ResponseBody
    // public byte[] exibirImagem(@PathVariable("idProduto") int idProduto){
    //     Produto p = produtoRepository.getById(idProduto);
    //     return p.getImagem();
    // }

    // @GetMapping("/AlterarProduto/{id}")
    // public ModelAndView alterarProduto(@PathVariable int id){
    //     ModelAndView mv = new ModelAndView("Adicionar");

    //     Produto produto = produtoRepository.getById(id);

    //     mv.addObject("produto", produto);

    //     return mv;
    // }

    // @PostMapping("/AlterarProduto/{id}")
    // public ModelAndView alterarProduto(@PathVariable int id,
    //                             @ModelAttribute("Produto") @Valid Produto p,
    //                             BindingResult bindingResult){
    //     if(bindingResult.hasErrors())
    //     {
    //         return new ModelAndView("Adicionar");
    //     }
    //     else
    //     {
    //         ModelAndView mv = new ModelAndView("redirect:/IndexbackOffice");

    //         Produto pAux = produtoRepository.getById(id);

    //         pAux.setNome(p.getNome());
    //         pAux.setAtivo(p.isAtivo());
    //         pAux.setCodigoDeBarras(p.getCodigoDeBarras());
    //         pAux.setDesconto(p.getDesconto());
    //         pAux.setDescricao(p.getDescricao());
    //         pAux.setEstoque(p.getEstoque());
    //         pAux.setFabricante(p.getFabricante());
    //         pAux.setModelo(p.getModelo());
    //         pAux.setPreco(p.getPreco());
    //         pAux.setImagem(p.getImagem());

    //         produtoRepository.save(pAux);

    //         return mv;
    //     }
    // }

    // @PostMapping("Excluir/{id}")
    // public ModelAndView excluirProduto(@PathVariable int id){

    //     Produto produto = produtoRepository.getById(id);

    //     if(produto == null)
    //     {
    //         return new ModelAndView("redirect:/IndexBackOffice");
    //     }
    //     else
    //     {
    //         ModelAndView mv = new ModelAndView("redirect:/IndexBackOffice");

    //         produto.setAtivo(false);

    //         produtoRepository.save(produto);

    //         return mv;
    //     }
    // }

}

