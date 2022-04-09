// package sp.senac.br.petshop.Validator;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import sp.senac.br.petshop.model.Usuario;
// import sp.senac.br.petshop.repository.UsuarioRepository;




// @Service
// public class LoginService implements UserDetailsService 
// {

//     @Autowired
//     UsuarioRepository usuarioRepository;

//     List<Usuario> usuarios;

//     @Override
//     public Usuario loadUserByUsername(String email) throws UsernameNotFoundException 
//     {
//         usuarios = usuarioRepository.buscaUsuariosAtivos();

//         for (Usuario u : usuarios) 
//         {
//             if(u.getEmail().equals(email))
//             {
//                 return u;
//             }
//         }
//         throw new UsernameNotFoundException("Usuário não encontrado!");
//     }
// }
