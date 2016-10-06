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
					"O CPF informado já está cadastrado para um funcionário.");
		}
	}

	@Test
	public void cpfInvalidoTest() {
		Funcionario f = new Funcionario("Leandro", "21347472828", "Gerente");
		try {
			f.salvar();
			fail("O Funcionario nao pode ser cadastrado com um CPF inválido.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O CPF informado é inválido.");
		}
	}

	public void nomeVazioTest() {
		Funcionario f = new Funcionario("", "10054908213", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario é obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O Nome é obrigatório.");
		}
	}

	@Test
	public void nomeVazioCpfDuplicadoTest() {
		Funcionario f = new Funcionario("", "06106775001", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario é obrigatorio. \n O Funcionario nao pode ser cadastrado com um CPF ja cadastrado.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 2);
			assertEquals(e.getMensagens()[0], "O Nome é obrigatório.");
			assertEquals(e.getMensagens()[1],
					"O CPF informado já está cadastrado para um funcionário.");
		}
	}

	@Test
	public void nomeVazioCpfInvalidoTest() {
		Funcionario f = new Funcionario("", "21347472828", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario é obrigatorio. \n O Funcionario nao pode ser cadastrado com um CPF inválido.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 2);
			assertEquals(e.getMensagens()[0], "O Nome é obrigatório.");
			assertEquals(e.getMensagens()[1], "O CPF informado é inválido.");

		}
	}

	@Test
	public void cpfVazioTest() {
		Funcionario f = new Funcionario("Leandro", "", "Gerente");
		try {
			f.salvar();
			fail("O CPF do Funcionario é obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O CPF é obrigatório.");

		}
	}

	@Test
	public void nomeVazioCpfVazioTest() {
		Funcionario f = new Funcionario("", "", "Gerente");
		try {
			f.salvar();
			fail("O nome do Funcionario é obrigatorio. \n O CPF do Funcionario é obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 2);
			assertEquals(e.getMensagens()[0], "O Nome é obrigatório.");
			assertEquals(e.getMensagens()[1], "O CPF é obrigatório.");
		}
	}

}
