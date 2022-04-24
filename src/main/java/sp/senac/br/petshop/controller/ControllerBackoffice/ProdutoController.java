package sp.senac.br.petshop.controller.ControllerBackoffice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.validation.Valid;

import org.hibernate.id.GUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.Utils.FileUploadUtil;
import sp.senac.br.petshop.model.Categoria;
import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.CategoriaRepository;
import sp.senac.br.petshop.repository.ProdutoRepository;

@RestController
@RequestMapping("/Indexbackoffice/Produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;
    

    @GetMapping
    public ModelAndView Listar(){
        ModelAndView mv = new ModelAndView("/Produtos/Listar");

        List<Produto> produtos = produtoRepository.buscaProdutosOrdenandoPorStatus();

        mv.addObject("produtos", produtos);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView Adicionar(){

        ModelAndView mv = new ModelAndView("/Produtos/Adicionar");

        List<Categoria> categorias = categoriaRepository.buscaCategoriasAtivas();

        mv.addObject("produto", new Produto());

        mv.addObject("categorias", categorias);

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView Adicionar(@ModelAttribute("produto") @Valid Produto p, @RequestParam("imagem") MultipartFile imagem, BindingResult bindingResult) throws IOException
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("/Produtos/Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/Produtos");

            String arquivo = StringUtils.cleanPath(imagem.getOriginalFilename());

            p.setImagemParaSalvar(arquivo);
            p.setAtivo(true);

            String diretorioSalvarImagem = "produtosImagens/" + UUID.randomUUID();
 
            FileUploadUtil.saveFile(diretorioSalvarImagem, arquivo, imagem);

            produtoRepository.save(p);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id)
    {
        Produto p = produtoRepository.findById(id).get();

        if(p == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/Produtos");
        }
    
        ModelAndView mv = new ModelAndView("/Produtos/Adicionar");

        List<Categoria> categorias = categoriaRepository.buscaCategoriasAtivas();

        mv.addObject("produto", p);

        mv.addObject("categorias", categorias);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id, @ModelAttribute("produto") @Valid Produto p, @RequestParam("imagem") MultipartFile imagem, BindingResult bindingResult) throws IOException
    {
        if(bindingResult.hasErrors())
        {
            return new ModelAndView("Adicionar");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/Produtos");

            Produto pAux = produtoRepository.findById(id).get();

            if(pAux == null)
            {
                return new ModelAndView("redirect:/Indexbackoffice/Produtos");
            }

            pAux.setNome(p.getNome());
            pAux.setCodigoDeBarras(p.getCodigoDeBarras());
            pAux.setDesconto(p.getDesconto());
            pAux.setDescricao(p.getDescricao());
            pAux.setEstoque(p.getEstoque());
            pAux.setFabricante(p.getFabricante());
            pAux.setIdCategoria(p.getIdCategoria());
            pAux.setModelo(p.getModelo());
            pAux.setPreco(p.getPreco());
            pAux.setPrecoDesconto(p.getPrecoDesconto());

            String arquivo = StringUtils.cleanPath(imagem.getOriginalFilename());

            pAux.setImagemParaSalvar(arquivo);

            String diretorioSalvarImagem = "produtosImagens/";
 
            FileUploadUtil.saveFile(diretorioSalvarImagem, arquivo + "_" + UUID.randomUUID(), imagem);

            produtoRepository.save(pAux);

            return mv;
        }
    }

    @GetMapping("/Excluir/{id}")
    public ModelAndView Excluir(@PathVariable int id)
    {
        Produto p = produtoRepository.findById(id).get();

        if(p == null)
        {
            return new ModelAndView("redirect:/Indexbackoffice/Produtos");
        }

        if(p.getAtivo())
        {
            p.setAtivo(false); 
        }
        else
        {
            p.setAtivo(true);
        }

        produtoRepository.save(p);

        return new ModelAndView("redirect:/Indexbackoffice/Produtos");
    }

    @GetMapping("Detalhes/{id}")
    public ModelAndView detalhe(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("Produtos/Detalhes");

        Produto p = produtoRepository.findById(id).get();

        mv.addObject("produto", p);

        return mv;
    }
}


