package sp.senac.br.petshop.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.ProdutoRepository;
import sp.senac.br.petshop.repository.UsuarioRepository;

@RestController
@RequestMapping("/backoffice")
public class IndexBackOfficeController {
    
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public ModelAndView index()
    {
        ModelAndView mv = new ModelAndView("/indexBackOffice");

        List<Cliente> clientes = clienteRepository.findAll();

        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView listarClientes()
    {
        ModelAndView mv = new ModelAndView("Adicionar");

        mv.addObject("cliente", new Cliente());

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView adicionar(@ModelAttribute("cliente") @Valid Cliente c, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Index");

            c.setAtivo(true);

            clienteRepository.save(c);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView alterarCliente(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("Adicionar");

        Cliente c = clienteRepository.getById(id);

        mv.addObject("cliente", c);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView alterarCliente(@PathVariable int id,
                                @ModelAttribute("cliente") @Valid Cliente c,
                                BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Index");

            Cliente cAux = clienteRepository.getById(id);

            cAux.setAtivo(c.getAtivo());
            cAux.setCPF(c.getCPF());
            cAux.setDataNascimento(c.getDataNascimento());
            cAux.setEmail(c.getEmail());
            cAux.setName(c.getName());
            cAux.setSexo(c.getSexo());
            cAux.setSobrenome(c.getSobrenome());
            cAux.setTelefone(c.getTelefone());

            clienteRepository.save(cAux);

            return mv;
        }
    }

    @PostMapping("Excluir/{id}")
    public ModelAndView excluirCliente(@PathVariable int id)
    {
        Cliente c = clienteRepository.getById(id);

        if(c == null)
        {
            return new ModelAndView("redirect:/IndexBackOffice");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/IndexBackOffice");

            c.setAtivo(false);

            clienteRepository.save(c);

            return mv;
        }
    }
    @GetMapping("/produto")
    public ModelAndView listarProdutos(){

        ModelAndView mv = new ModelAndView("produtos");
        List<Produto> produtos = produtoRepository.findAll();
        mv.addObject("produto", produtos);
        
        return mv;
    }

    @GetMapping("/cadastroProduto")
    public ModelAndView addProduto(){

        ModelAndView mv = new ModelAndView("cadastroProduto");

        mv.addObject("produto", new Produto());

        return mv;
    }

    @PostMapping("/cadastroProduto")
    public ModelAndView salvarProduto(@RequestParam("fileProduto") MultipartFile file,
    @ModelAttribute("produto") @Valid Produto p, BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("cadastroProduto");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/IndexBackOffice");
                    try{
                     p.setImagem(file.getBytes());   
                    }catch(IOException e){
                        e.printStackTrace();
                    }

            p.setAtivo(true);

            produtoRepository.save(p);

            return mv;
        }
    }

    @GetMapping("/imagem/{idProduto}")
    @ResponseBody
    public byte[] exibirImagem(@PathVariable("idProduto") int idProduto){
        Produto p = produtoRepository.getById(idProduto);
        return p.getImagem();
    }

    @GetMapping("/AlterarProduto/{id}")
    public ModelAndView alterarProduto(@PathVariable int id){
        ModelAndView mv = new ModelAndView("Adicionar");

        Produto produto = produtoRepository.getById(id);

        mv.addObject("produto", produto);

        return mv;
    }

    @PostMapping("/AlterarProduto/{id}")
    public ModelAndView alterarProduto(@PathVariable int id,
                                @ModelAttribute("Produto") @Valid Produto p,
                                BindingResult bindingResult){
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/IndexBackOffice");

            Produto pAux = produtoRepository.getById(id);

            pAux.setNome(p.getNome());
            pAux.setAtivo(p.isAtivo());
            pAux.setCodigoDeBarras(p.getCodigoDeBarras());
            pAux.setDesconto(p.getDesconto());
            pAux.setDescricao(p.getDescricao());
            pAux.setEstoque(p.getEstoque());
            pAux.setFabricante(p.getFabricante());
            pAux.setModelo(p.getModelo());
            pAux.setPreco(p.getPreco());
            pAux.setImagem(p.getImagem());

            produtoRepository.save(pAux);

            return mv;
        }
    }

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
