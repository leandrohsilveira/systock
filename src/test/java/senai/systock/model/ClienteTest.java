package senai.systock.model;

import static org.junit.Assert.*;
import org.junit.Test;

import senai.systock.exceptions.ValidationException;

public class ClienteTest {
	
	@Test
	public void sucessoTest() throws ValidationException {
		Cliente cliente = new Cliente("Leandro","76042714210","leandro@gmail.com");
		cliente.cadastrar();
	}
	
	@Test
	public void cpfDuplicadoTest() {
		Cliente cliente = new Cliente("Leandro 1","29697824991","leandro1@gmail.com");	
		try {
			cliente.cadastrar();
			fail("O cpf do cliente já existe.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "Já existe um cliente cadastrado com este CPF!");
		}		
	}
	
	@Test
	public void cpfInvalidoTest() {
		Cliente cliente = new Cliente("Leandro 1","29697822941","leandro1@gmail.com");	
		try {
			cliente.cadastrar();
			fail("O cpf não é valido.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O CPF informado é inválido!");
		}		
	}
	
	@Test
	public void emailDuplicadoTest() {
		Cliente cliente = new Cliente("Leandro 1","55587135853","cliente.teste@gmail.com");	
		try {
			cliente.cadastrar();
			fail("O email já esta cadastrado.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O E-mail já está cadastrado para um cliente!");
		}
	}
	
	@Test
	public void emailInvalidoTest1() {
		Cliente cliente = new Cliente("Leandro 1","55587135853","leandro@");	
		try {
			cliente.cadastrar();
			fail("O email esta com formato inválido.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O E-mail informado é inválido!");
		}		
	}
	
	@Test
	public void emailInvalidoTest2() {
		Cliente cliente = new Cliente("Leandro 1","55587135853","@gmail.com");	
		try {
			cliente.cadastrar();
			fail("O email esta com formato inválido.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O E-mail informado é inválido!");
		}		
	}
	
	@Test
	public void nomeVazioTest() {
		Cliente cliente = new Cliente(null, "55587135853","leandro1@gmail.com");
		try {
			cliente.cadastrar();
			fail("O nome está em branco.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O nome não pode esta em branco!");
		}		
	}
	
	@Test
	public void cpfVazioTest() {
		Cliente cliente = new Cliente("Leandro 1", null, "leandro1@gmail.com");
		try {
			cliente.cadastrar();
			fail("O cpf esta em branco.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O cpf não pode esta em branco!");
		}		
	}
	
	@Test
	public void emailVazioTest() {
		Cliente cliente = new Cliente("Leandro 1", "55587135853", null);	
		try {
			cliente.cadastrar();
			fail("O email esta em branco.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "	O e-mail nâo pode esta em branco!");
		}		
	}

}
