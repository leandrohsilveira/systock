package senai.systock.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(path="/auth", consumes=MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public void login(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		logger.info("Login: {}/{}", login, senha);
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login, senha));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	@GetMapping(path="/logout")
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
	}
	
}
