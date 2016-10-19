package senai.systock.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import senai.systock.model.Cargo;
import senai.systock.model.Usuario;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CsrfTokenRepository csrfTokenRepository;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/bower.json").denyAll()
			.antMatchers("/paginas/usuario/**", "/usuarios/**", "/paginas/funcionario/**", "/funcionarios/**").hasAuthority(Cargo.ADMINISTRADOR.name())
			.antMatchers("/produtos/**", "/vendas/**", "/clientes/**", "/paginas/**").authenticated()
			.mvcMatchers(HttpMethod.DELETE, "**").denyAll()
			.anyRequest().permitAll()
			.and().exceptionHandling().authenticationEntryPoint(new Http401AuthenticationEntryPoint("AngularJS"))
			.and().csrf().csrfTokenRepository(csrfTokenRepository);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery("select login, secret, ativo from usuario where login=?")
				.authoritiesByUsernameQuery("select u.login, f.cargo from usuario u join funcionario f on f.id = u.funcionario_id where u.login = ?")
				.passwordEncoder(Usuario.passwordEncoder);
	}
	
	@Bean
	public CsrfTokenRepository csrfTokenRepository() {
		return new HttpSessionCsrfTokenRepository();
	}
	
}