package senai.systock.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestController
public class FuncionarioController {
	
	@RequestMapping("/funcionario")
	public String hello() {
		return "Hello";
	}
	
}