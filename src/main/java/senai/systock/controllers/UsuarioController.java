package senai.systock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import senai.systock.dao.UsuarioRepository;
import senai.systock.model.Usuario;

@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@JsonView(Usuario.SemSenhaView.class)
	@GetMapping("/usuarios/{id:\\d+}")
	public Usuario getUsuario(@PathVariable("id") Long id) {
		return usuarioRepository.findOne(id);
	}
	
	@JsonView(Usuario.SemSenhaView.class)
	@GetMapping("/usuarios/teste")
	public Usuario cadastrarTeste(@RequestParam("login") String login, @RequestParam("senha") String senha) {
		return usuarioRepository.save(new Usuario(login, senha));
	}
	
}
