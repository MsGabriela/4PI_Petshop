package sp.senac.br.petshop.controller.ControllerBackoffice;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import sp.senac.br.petshop.model.Funcionario;
import sp.senac.br.petshop.model.tipoAcesso;
import sp.senac.br.petshop.repository.FuncionarioRepository;
import sp.senac.br.petshop.repository.TipoAcessoRepository;
import sp.senac.br.petshop.repository.UsuarioRepository;

@RestController
@RequestMapping("/Indexbackoffice/Usuarios")
public class UsuarioBackOfficeControler {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @Autowired
    FuncionarioRepository funcianarioRepository;

    @Autowired
    TipoAcessoRepository tipoAcessoRepository;

    private BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @GetMapping
    public ModelAndView Listar() {
        ModelAndView mv = new ModelAndView("/Usuarios/Listar");

        List<Funcionario> funcionario = funcianarioRepository.findAll();

        mv.addObject("funcionarios", funcionario);

        return mv;
    }

    @GetMapping("/Adicionar")
    public ModelAndView Adicionar() {
        ModelAndView mv = new ModelAndView("/Usuarios/Adicionar");

        List<tipoAcesso> tipoAcessos = tipoAcessoRepository.findAll();

        mv.addObject("funcionario", new Funcionario());
        mv.addObject("tipoAcessos", tipoAcessos);

        return mv;
    }

    @PostMapping("/Adicionar")
    public ModelAndView Adicionar(@ModelAttribute("funcionario") @Valid Funcionario f, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/Usuarios/Adicionar");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/Usuarios");

            f.setAtivo(true);

            f.setHashSenha(passwordEncoder().encode(f.getHashSenha()));
            funcianarioRepository.save(f);

            return mv;
        }
    }

    @GetMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id) {

        Funcionario f = funcianarioRepository.findById(id).get();

        if (f == null) {
            return new ModelAndView("redirect:/Usuarios");
        }

        ModelAndView mv = new ModelAndView("/Usuarios/Adicionar");

        mv.addObject("funcionario", f);

        return mv;
    }

    @PostMapping("/Alterar/{id}")
    public ModelAndView Alterar(@PathVariable int id,
            @ModelAttribute("cliente") @Valid Funcionario f,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("Adicionar");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/Usuarios");

            Funcionario fAux = funcianarioRepository.findById(id).get();

            if (fAux == null) {
                return new ModelAndView("redirect:/Usuarios");
            }

            fAux.setCPF(f.getCPF());
            fAux.setEmail(f.getEmail());
            fAux.setNome(f.getNome());
            fAux.setSobrenome(f.getSobrenome());
            fAux.setHashSenha(f.getHashSenha());


            funcianarioRepository.save(fAux);

            return mv;
        }
    }

    @GetMapping("/Excluir/{id}")
    public ModelAndView excluirFuncianario(@PathVariable int id) {
        
        Funcionario f = funcianarioRepository.findById(id).get();

        if (f == null) {
            return new ModelAndView("redirect:/Usuarios");
        }

        if (f.isAtivo()) {
            f.setAtivo(false);
        } else {
            f.setAtivo(true);
        }


        funcianarioRepository.save(f);

        return new ModelAndView("redirect:/Usuarios");
    }
}