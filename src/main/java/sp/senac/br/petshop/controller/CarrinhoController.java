package sp.senac.br.petshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Compra;
import sp.senac.br.petshop.model.ItemsCompra;
import sp.senac.br.petshop.model.Produto;
import sp.senac.br.petshop.repository.ProdutoRepository;

@RestController
public class CarrinhoController {

    private List<ItemsCompra> itemsCompra = new ArrayList<ItemsCompra>();

    @Autowired
    private ProdutoRepository produtoRepository;

    private Compra compra = new Compra();

    private void CalcularTotal(){
        compra.setValorTotal(0);
        for (ItemsCompra ic : itemsCompra) {
            compra.setValorTotal(compra.getValorTotal() + ic.getValorTotal());
        }
    }

    @GetMapping("/Carrinho")
    public ModelAndView carrinho() {

        ModelAndView mv = new ModelAndView("Carrinho/Carrinho");
        CalcularTotal();
        mv.addObject("compra", compra);
        mv.addObject("listaItems", itemsCompra);
        return mv;

    }

    @GetMapping("/Alterar/{id}/{acao}")
    public ModelAndView AlterarQuantidade(@PathVariable int id, @PathVariable Integer acao) {

        ModelAndView mv = new ModelAndView("Carrinho/Carrinho");

        for (ItemsCompra ic : itemsCompra) {
            if (ic.getProduto().getIdProduto() == id) {
               if(acao == 1){
                   ic.setQuantidade(ic.getQuantidade()+1);
                   ic.setValorTotal(0);
                   ic.setValorTotal(ic.getValorTotal() + (ic.getValorUnitario() * ic.getQuantidade()));
                   CalcularTotal();
               }else if(acao == 0){
                   ic.setQuantidade(ic.getQuantidade()-1);
                   ic.setValorTotal(0);
                   ic.setValorTotal(ic.getValorTotal() + (ic.getValorUnitario() * ic.getQuantidade()));
                   CalcularTotal();
               }
               break;
            }
        }
        mv.addObject("compra", compra);
        mv.addObject("listaItems", itemsCompra);
        return mv;

    }

    @GetMapping("/adicionarCarrinho/{id}")
    public ModelAndView Adicionar(@PathVariable int id) {

        ModelAndView mv = new ModelAndView("Carrinho/Carrinho");
        Optional<Produto> prod = produtoRepository.findById(id);
        Produto produto = prod.get();

        int controle = 0;

        for (ItemsCompra ic : itemsCompra) {
            if (ic.getProduto().getIdProduto() == produto.getIdProduto()) {
                ic.setQuantidade(ic.getQuantidade() + 1);
                ic.setValorTotal(0);
                ic.setValorTotal(ic.getValorTotal() + (ic.getValorUnitario() * ic.getQuantidade()));
                CalcularTotal();
                controle = 1;
                break;
            }
        }
        if (controle == 0) {

            ItemsCompra item = new ItemsCompra();
            item.setProduto(produto);
            item.setValorUnitario(produto.getPreco());
            item.setQuantidade(item.getQuantidade() + 1);
            item.setValorTotal(0);
            item.setValorTotal(item.getValorTotal() + (item.getValorUnitario() * item.getQuantidade()));
            itemsCompra.add(item);

        }
        CalcularTotal();
        mv.addObject("compra", compra); 
        mv.addObject("listaItems", itemsCompra);
        return mv;

    }


    @GetMapping("/Remover/{id}")
    public ModelAndView remover(@PathVariable int id) {

        ModelAndView mv = new ModelAndView("Carrinho/Carrinho");

        for (ItemsCompra ic : itemsCompra) {
            if(ic.getProduto().getIdProduto() == id){
                itemsCompra.remove(ic);
                ic.setValorTotal(ic.getValorTotal() + (ic.getValorUnitario() * ic.getQuantidade()));
                break;
            }
        }

        mv.addObject("compra", compra); 
        mv.addObject("listaItems", itemsCompra);
        return mv;

    }
}
