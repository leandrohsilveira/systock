package senai.systock.controllers;

import java.util.ArrayList;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import senai.systock.json.LoginJson;
import senai.systock.model.Usuario;
import senai.systock.repository.UsuarioRepository;

@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CsrfTokenRepository csrfTokenRepository;
	
	@RequestMapping(method=RequestMethod.HEAD, path="/")
	public void saveCsrfToken(HttpServletRequest request, HttpServletResponse response) {
		CsrfToken token = csrfTokenRepository.generateToken(request);
		csrfTokenRepository.saveToken(token, request, response);
		response.setHeader(token.getHeaderName(), token.getToken());
	}
	
	@PostMapping(path="/auth", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		LoginJson json = new LoginJson(login, senha);
		Set<ConstraintViolation<Object>> violations = json.validar();
		if(violations.isEmpty()) {
			try {
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch(BadCredentialsException e) {
				throw new UsuarioSenhaInvalidosException();
			}
		} else {
			throw new ConstraintViolationException(violations);
		}
	}
	
	@GetMapping(path="/auth", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> getAuth() {
		String login = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioRepository.findByLogin(login);
		if(usuario != null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}
		return new ResponseEntity<Usuario>(HttpStatus.NO_CONTENT);
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
