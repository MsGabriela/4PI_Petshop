package sp.senac.br.petshop.controller.ControllerBackoffice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Cliente;
import sp.senac.br.petshop.repository.ClienteRepository;
import sp.senac.br.petshop.repository.UsuarioRepository;

@RestController
@RequestMapping("/Indexbackoffice/Usuarios")
public class UsuarioBackOfficeControler {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ModelAndView Listar() {
        ModelAndView mv = new ModelAndView("Usuarios/Listar");

        List<Cliente> clientes = clienteRepository.findAll();

        mv.addObject("clientes", clientes);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView Adicionar() {
        ModelAndView mv = new ModelAndView("/Usuarios/Adicionar");

        mv.addObject("cliente", new Cliente());

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView Adicionar(@ModelAttribute("cliente") @Valid Cliente c, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/Usuarios/Adicionar");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/Usuarios");

            c.setAtivo(true);

            clienteRepository.save(c);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id) {

        Cliente c = clienteRepository.findById(id).get();

        if (c == null) {
            return new ModelAndView("redirect:/Indexbackoffice/Usuarios");
        }

        ModelAndView mv = new ModelAndView("/Usuarios/Adicionar");

        mv.addObject("cliente", c);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id,
            @ModelAttribute("cliente") @Valid Cliente c,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("Adicionar");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/Indexbackoffice/Usuarios");

            Cliente cAux = clienteRepository.findById(id).get();

            if (cAux == null) {
                return new ModelAndView("redirect:/Indexbackoffice/Usuarios");
            }

            cAux.setAtivo(c.getAtivo());
            cAux.setCPF(c.getCPF());
            cAux.setDataNascimento(c.getDataNascimento());
            cAux.setEmail(c.getEmail());
            cAux.setConfirmarEmail(c.getConfirmarEmail());
            cAux.setName(c.getName());
            cAux.setSexo(c.getSexo());
            cAux.setSobrenome(c.getSobrenome());
            cAux.setTelefone(c.getTelefone());
            cAux.setHashSenha(c.getHashSenha());
            cAux.setConfirmarSenha(c.getConfirmarSenha());

            clienteRepository.save(cAux);

            return mv;
        }
    }

    @GetMapping("/Excluir/{id}")
    public ModelAndView excluirCliente(@PathVariable int id) {
        
        Cliente c = clienteRepository.findById(id).get();

        if (c == null) {
            return new ModelAndView("redirect:/IndexbackOffice/Usuarios");
        }

        if (c.getAtivo()) {
            c.setAtivo(false);
        } else {
            c.setAtivo(true);
        }

        clienteRepository.save(c);

        return new ModelAndView("redirect:/IndexbackOffice/Usuarios");
    }
}