package senai.systock.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import senai.systock.dao.UsuarioRepository;
import senai.systock.model.EntidadeBase;
import senai.systock.model.EntidadeBase.Public;
import senai.systock.model.Funcionario;
import senai.systock.model.Usuario;

@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping(path="/auth", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} catch(BadCredentialsException e) {
			throw new UsuarioSenhaInvalidosException();
		}
	}
	
	@JsonView(Public.class)
	@GetMapping(path="/auth", produces=MediaType.APPLICATION_JSON_VALUE)
	public Usuario getAuth() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		return usuarioRepository.findByLogin(login);
	}
	
	@GetMapping(path="/logout")
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
	@ResponseStatus(code=HttpStatus.FORBIDDEN, reason="Usuário e/ou senha inválidos.")
	protected class UsuarioSenhaInvalidosException extends RuntimeException {

		private static final long serialVersionUID = 4029375442396250945L;
		
	}
	
}
