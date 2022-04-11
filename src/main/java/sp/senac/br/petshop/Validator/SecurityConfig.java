package sp.senac.br.petshop.Validator;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter
{
    
    public static PasswordEncoder plainPasswordEncoder()
    {
        
        return new PasswordEncoder() 
        {
            @Override
            public String encode(CharSequence cs)
            {
                return cs.toString();
            }

            @Override
            public boolean matches(CharSequence cs, String salt)
            {
                return cs.toString().equals(salt);
            }
        };
    }

    public static PasswordEncoder bcryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return bcryptPasswordEncoder();
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception
    {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/img**", "/js/**", "/fonts/**", "/scss/**", "/vendor/**").permitAll()
                .and()
                .formLogin()
                    .loginPage("/login")
                    .usernameParameter("email")
                    .passwordParameter("senha")
                    .defaultSuccessUrl("/admin").permitAll()
                    .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true).deleteCookies("JSESSIONID");
    }

    @Bean
    public static AuthenticationFailureHandler authenticationFailureHandler() 
    {
        return new CustomAuthenticationFailureHandler();
    }

}
