package sp.senac.br.petshop.controller.controllerBackoffice;



import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.model.Usuario;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.PedidoRepository;
import sp.senac.br.petshop.repository.ProdutoRepository;
//import sp.senac.br.petshop.repository.UsuarioRepository;

import javax.validation.Valid;
//import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class BackofficeController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    // @Autowired
    // private CategoriaRepository categoriaRepository;

    // @Autowired
    // private PedidoRepository pedidoRepository;

    @GetMapping
    public ModelAndView IndexBackoffice(){
        ModelAndView mv = new ModelAndView("IndexBackoffice");

        return mv;
    }

    @GetMapping("/usuariosBackoffice/lista")
    public ModelAndView usuariosBackoffice(){
        ModelAndView mv = new ModelAndView("usuariosBackoffice");

        List<Cliente> clientes = clienteRepository.buscaUsuariosAtivos();

        mv.addObject("usuarios", clientes);
        return mv;
    }

    @GetMapping("/usuariosBackoffice")
    public ModelAndView cadastraUsuarios(){
        ModelAndView mv = new ModelAndView("usuariosBackofficeCadastro");

        mv.addObject("usuario", new Cliente());
        return mv;
    }

    @PostMapping("/usuariosBackoffice")
    public ModelAndView cadastraUsuarios(
            @ModelAttribute("usuario")  @Valid Cliente c,
            BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return new ModelAndView("usuariosBackofficeCadastro");
        }
        else{
            ModelAndView mv = new ModelAndView("redirect:/admin");

            c.setNome(c.getNome() + " " + c.getSobrenome());
            c.setSenha(c.getHashSenha());
            c.setAtivo(true);

            clienteRepository.save(c);
            return mv;
        }

    }

    @GetMapping("/excluirUsuario/{id}")
    public ModelAndView excluirUsuario(@PathVariable int id){
        ModelAndView mv = new ModelAndView("redirect:/admin");

        Cliente c = clienteRepository.getById(id);

        c.setAtivo(false);

        int espaco = c.getNome().indexOf(" ");

        c.setSobrenome(c.getNome().substring(espaco+1));
        c.setNome(c.getNome().substring(0, espaco));

        clienteRepository.save(c);

        return mv;
    }

    @GetMapping("/alterarUsuario/{id}")
    public ModelAndView alterarUsuario(@PathVariable int id){
        ModelAndView mv = new ModelAndView("usuariosBackofficeCadastro");

        //List<Cliente> clientes = clienteRepository.buscaUsuariosAtivos();

        Usuario u = clienteRepository.getById(id);

        int espaco = u.getNome().indexOf(" ");

        u.setSobrenome(u.getNome().substring(espaco+1));
        u.setNome(u.getNome().substring(0, espaco));

        //mv.addObject("usuarios", usuarios);
        mv.addObject("usuario", u);
        return mv;
    }

    @PostMapping("/alterarUsuario/{id}")
    public ModelAndView alterarUsuario(@PathVariable int id, @ModelAttribute("usuario")  @Valid Cliente c,
                                       BindingResult bindingResult){

        if(bindingResult.hasErrors()){
           List<Cliente> clientes = clienteRepository.buscaUsuariosAtivos();
            return new ModelAndView("usuariosBackofficeCadastro").addObject("usuarios", clientes);
        }
        else{
            ModelAndView mv = new ModelAndView("redirect:/admin");

            Cliente client = clienteRepository.getById(id);
            client.setNome(c.getNome());
            client.setSobrenome(c.getSobrenome());
            client.setSenha(c.getHashSenha());
            client.setCPF(c.getCPF());
            client.setEmail(c.getEmail());
            client.setDataNascimento(c.getDataNascimento());
            client.setSexo(c.getSexo());
            client.setTelefone(c.getTelefone());
            client.setTipoAcesso(c.getTipoAcesso());

            client.setNome(c.getNome() + " " + c.getSobrenome());

            clienteRepository.save(client);

            return mv;
        }
    }


    //Produtos e Categorias

    @GetMapping("/adicionar")
    public ModelAndView adicionar(){
        ModelAndView mv = new ModelAndView("cadastroProduto");

        //List<Categoria> categorias = categoriaRepository.findAll();

        // for(Categoria c : categorias){
        //     System.out.println(c.getIdCategoria());
        //     System.out.println(c.getNome());
        // }

        mv.addObject("produto", new Produto());
    //     // mv.addObject("categorias", categorias);


        return mv;
    }

    // @PostMapping("/adicionar")
    // public ModelAndView adicionar(
    //        @ModelAttribute("produto") @Valid Produto p, BindingResult bindingResult){

    //     if(bindingResult.hasErrors()){
    //         System.out.println("Erros");
    //         System.out.println(bindingResult);
    //         List<Categoria> categorias = categoriaRepository.findAll();
    //        return new ModelAndView("cadastroProduto").addObject("categorias", categorias).addObject("produto", p);
    //     }
    //     else {
    //         ModelAndView mv = new ModelAndView("redirect:/admin");

    //         p.setPrecoDesconto(p.getPreco() - (p.getPreco() * ((double) p.getDesconto() / 100)));

    //         produtoRepository.save(p);

    //         List<Produto> produtos = produtoRepository.findAll();

    //         mv.addObject("produtos", produtos);

    //         mv.addObject("insert", true);

    //         return mv;
    //     }
    // }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id){
        

         ModelAndView mv = new ModelAndView("cadastroProduto");

        //List<Categoria> categorias = categoriaRepository.findAll();
        Produto p = produtoRepository.getOne(id);

        mv.addObject("produto", p);
       // mv.addObject("categorias", categorias);

        return mv;
        
    }

    @PostMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable int id, @ModelAttribute("produto")  @Valid Produto p,
                                       BindingResult bindingResult){
        
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("cadastroProduto").addObject("produto", p);
        } else {
        ModelAndView mv = new ModelAndView("redirect:/admin/listagemProdutosBackOffice");

        Produto prod = produtoRepository.getById(id);

        double precoDesconto = p.getPreco() - (p.getPreco() * ((double)p.getDesconto() / 100));
        int precoDescontoRounded = (int) Math.round(precoDesconto);
        prod.setDescricao(p.getDescricao());
        prod.setAtivo(p.getAtivo());
        prod.setNome(p.getNome());
        prod.setPreco(p.getPreco());
        prod.setEstoque(p.getEstoque());
        prod.setIdCategoria((p.getIdCategoria()));
        prod.setModelo(p.getModelo());
        prod.setCodigoDeBarras(p.getCodigoDeBarras());
        prod.setDesconto(p.getDesconto());
        prod.setPrecoDesconto(new Double(precoDescontoRounded));

        System.out.println("\n\n" + prod.getPrecoDesconto() + "\n\n");

        produtoRepository.save(prod);

        return mv;
        }
        
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable int id){

        ModelAndView mv = new ModelAndView("redirect:/admin/listagemProdutosBackOffice");

        Produto p = produtoRepository.getOne(id);
        p.setAtivo(false);

        produtoRepository.save(p);

        mv.addObject("sucesso", true);

        return mv;

    }

    @GetMapping("/listagemProdutosBackOffice")
    public ModelAndView listagemProdutosBackOffice(){
        ModelAndView mv = new ModelAndView("listagemProdutosBackOffice");
        List<Produto> produtos = produtoRepository.findAll();

        mv.addObject("produtos", produtos);


        return mv;
    }

    // @GetMapping("/listagemPedidoBackOffice")
    // public ModelAndView listagemPedidoBackOffice(){
    //     ModelAndView mv = new ModelAndView("listagemPedidoBackoffice");

    //     List<Pedido> pedidos = pedidoRepository.findAll();
    //     mv.addObject("pedidos", pedidos);


    //     return mv;
    // }

    // @GetMapping("/listagemPedidosBackOffice/validar/{id}")
    // public ModelAndView validaPedido(@PathVariable int id){
    //     ModelAndView mv = new ModelAndView("redirect:/admin/listagemPedidoBackOffice");

    //     Pedido p = pedidoRepository.getOne(id);

    //     p.setStatus(1);

    //     pedidoRepository.save(p);

    //     return mv;
    // }
    
    // @GetMapping("/listagemPedidosBackOffice/detalhar/{id}")
    // public ModelAndView detalhaPedido(@PathVariable int id){
    //     ModelAndView mv = new ModelAndView("detalhepedidobackoffice");

    //     Pedido p = pedidoRepository.getOne(id);

      

    //     return mv.addObject("pedido", p);
    // }

}
