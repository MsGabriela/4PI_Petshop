package sp.senac.br.petshop.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.EnderecoRepository;

@RestController
public class ClientesController {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    // @Autowired
    // private EnderecoRepository enderecoRepository;

    @GetMapping("Index")
    public ModelAndView index()
    {
        ModelAndView mv = new ModelAndView("Index");

        List<Cliente> clientes = clienteRepository.findAll();

        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView adicionar()
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
    public ModelAndView alterar(@PathVariable int id)
    {
        ModelAndView mv = new ModelAndView("Adicionar");

        Cliente c = clienteRepository.getById(id);

        mv.addObject("cliente", c);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView alterar(@PathVariable int id,
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
    public ModelAndView excluir(@PathVariable int id)
    {
        Cliente c = clienteRepository.getById(id);

        if(c == null)
        {
            return new ModelAndView("redirect:/Index");
        }
        else
        {
            ModelAndView mv = new ModelAndView("redirect:/Index");

            c.setAtivo(false);

            clienteRepository.save(c);

            return mv;
        }
    }
}
