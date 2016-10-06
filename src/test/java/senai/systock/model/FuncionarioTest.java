package senai.systock.model;

import org.junit.Test;

import senai.systock.exceptions.ValidationException;

import static org.junit.Assert.*;

public class FuncionarioTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Funcionario f = new Funcionario("Leandro", "10054908213", "Gerente");
		f.salvar();
	}

	@Test
	public void cpfDuplicadoTest() {
		Funcionario f = new Funcionario("Leandro", "06106775001", "Gerente");
		try {
			f.salvar();
			fail("O Funcionario nao pode ser cadastrado com um CPF ja cadastrado.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0],
					"O CPF informado j� est� cadastrado para um funcion�rio.");
		}
	}

	@Test
	public void cpfInvalidoTest() {
		Funcionario f = new Funcionario("Leandro", "21347472828", "Gerente");
		try {
			f.salvar();
			fail("O Funcionario nao pode ser cadastrado com um CPF inv�lido.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O CPF informado � inv�lido.");
		}
	}

	public void nomeVazioTest() {
		Funcionario f = new Funcionario("", "10054908213", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario � obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O Nome � obrigat�rio.");
		}
	}

	@Test
	public void nomeVazioCpfDuplicadoTest() {
		Funcionario f = new Funcionario("", "06106775001", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario � obrigatorio. \n O Funcionario nao pode ser cadastrado com um CPF ja cadastrado.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 2);
			assertEquals(e.getMensagens()[0], "O Nome � obrigat�rio.");
			assertEquals(e.getMensagens()[1],
					"O CPF informado j� est� cadastrado para um funcion�rio.");
		}
	}

	@Test
	public void nomeVazioCpfInvalidoTest() {
		Funcionario f = new Funcionario("", "21347472828", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario � obrigatorio. \n O Funcionario nao pode ser cadastrado com um CPF inv�lido.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 2);
			assertEquals(e.getMensagens()[0], "O Nome � obrigat�rio.");
			assertEquals(e.getMensagens()[1], "O CPF informado � inv�lido.");

		}
	}

	@Test
	public void cpfVazioTest() {
		Funcionario f = new Funcionario("Leandro", "", "Gerente");
		try {
			f.salvar();
			fail("O CPF do Funcionario � obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O CPF � obrigat�rio.");

		}
	}

	@Test
	public void nomeVazioCpfVazioTest() {
		Funcionario f = new Funcionario("", "", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario � obrigatorio. \n O CPF do Funcionario � obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 2);
			assertEquals(e.getMensagens()[0], "O Nome � obrigat�rio.");
			assertEquals(e.getMensagens()[1], "O CPF � obrigat�rio.");
		}
	}

}
