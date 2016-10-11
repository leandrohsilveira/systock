package senai.systock.model;

import org.junit.Test;

import senai.systock.exceptions.ValidationException;

import static org.junit.Assert.*;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Ignore;

@Ignore
public class ValidacaoFuncionarioTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Funcionario f = new Funcionario("Leandro", "10054908213", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.isEmpty());
	}

	@Test
	@Ignore
	public void cpfDuplicadoTest() {
		Funcionario f = new Funcionario("Leandro", "06106775001", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF já está cadastrado", message);
	}

	@Test
	public void cpfInvalidoTest() {
		Funcionario f = new Funcionario("Leandro", "21347472828", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF é inválido", message);
	}

	public void nomeVazioTest() {
		Funcionario f = new Funcionario("", "10054908213", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O Nome não pode ser vazio", message);
	}

	@Ignore
	@Test
	public void nomeVazioCpfDuplicadoTest() {
		Funcionario f = new Funcionario("", "06106775001", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 2);
		String message;
		message = validar.iterator().next().getMessage();
		assertEquals("O Nome não pode ser vazio", message);
		message = validar.iterator().next().getMessage();
		assertEquals("O CPF já está cadastrado", message);
	}

	@Test
	public void nomeVazioCpfInvalidoTest() {
		Funcionario f = new Funcionario("", "21347472828", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 2);
		String message;
		message = validar.iterator().next().getMessage();
		assertEquals("O Nome não pode ser vazio", message);
		message = validar.iterator().next().getMessage();
		assertEquals("O CPF é inválido", message);
	}

	@Test
	public void cpfVazioTest() {
		Funcionario f = new Funcionario("Leandro", "", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF não pode ser vazio", message);
	}

	@Test
	public void nomeVazioCpfVazioTest() {
		Funcionario f = new Funcionario("", "", "Gerente");
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 2);
		String message;
		message = validar.iterator().next().getMessage();
		assertEquals("O Nome não pode ser vazio", message);
		message = validar.iterator().next().getMessage();
		assertEquals("O CPF não pode ser vazio", message);
	}

}
