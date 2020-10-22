package web.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import web.Config.handler.LoginSuccessHandler;
import web.Service.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "web")
@EnableTransactionManagement(proxyTargetClass = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(userService);
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        // The pages does not require login
        http.authorizeRequests().antMatchers("/", "/login", "/logout").permitAll();

        // /userPage page requires login as ROLE_USER or ROLE_ADMIN.
        // If no login, it will redirect to /login page.
        http.authorizeRequests().antMatchers("/userPage").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')");

        // For ADMIN only.
        http.authorizeRequests().antMatchers("/admin").access("hasRole('ROLE_ADMIN')");

        // When the user has logged in as XX.
        // But access a page that requires role YY,
        // AccessDeniedException will be thrown.
        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");

        // Config for Login Form
        http.authorizeRequests().and().formLogin()//
                // Submit URL of login page.
                .loginProcessingUrl("/j_spring_security_check") // Submit URL
                .loginPage("/login")//
                .defaultSuccessUrl("/userAccountInfo")//
                .failureUrl("/login?error=true")//
                .usernameParameter("username")//
                .passwordParameter("password")
                // Config for Logout Page
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/home");

    }


/*        http.formLogin()
                .loginPage("/login")// указываем страницу с формой логина
                .successHandler(new LoginSuccessHandler())//указываем логику обработки при логине
                .loginProcessingUrl("/login") // указываем action с формы логина
                .usernameParameter("j_username")// Указываем параметры логина и пароля с формы логина
                .passwordParameter("j_password")
                .permitAll(); // даем доступ к форме логина всем

        http.logout()
                .permitAll()// разрешаем делать логаут всем
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))// указываем URL логаута
                .logoutSuccessUrl("/login?logout")  // указываем URL при удачном логауте
                .and().csrf().disable(); //выклчаем кроссдоменную секьюрность (на этапе обучения неважна)

        http
                .authorizeRequests()// делаем страницу регистрации недоступной для авторизированных пользователей
                .antMatchers("/login").anonymous() //страницы аутентификаци доступна всемc
                .antMatchers("/register").anonymous()
                .antMatchers("/hello").access("hasAnyRole('ADMIN')").anyRequest().authenticated();  // защищенные URL
    }
*/

/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/

}
