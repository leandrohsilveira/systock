package senai.systock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import senai.systock.model.Funcionario;

@RestController
public class FuncionarioController {
	
	@RequestMapping("/funcionario")
	public Funcionario hello() {
		return new Funcionario("Leandro", "12345678901", "Administrador");
	}
	
}