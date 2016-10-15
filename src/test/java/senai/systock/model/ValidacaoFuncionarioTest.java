package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Ignore;
import org.junit.Test;

import senai.systock.exceptions.ValidationException;

public class ValidacaoFuncionarioTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Funcionario f = new Funcionario("Leandro", "10054908213", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertTrue(validar.isEmpty());
	}

	@Test
	@Ignore
	public void cpfDuplicadoTest() {
		Funcionario f = new Funcionario("Leandro", "06106775001", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(1, validar.size());
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF já está cadastrado", message);
	}

	@Test
	public void cpfInvalidoTest() {
		Funcionario f = new Funcionario("Leandro", "21347472828", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(1, validar.size());
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF é inválido", message);
	}
	
	@Test
	public void nomeVazioTest() {
		Funcionario f = new Funcionario("", "10054908213", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(1, validar.size());
		String message = validar.iterator().next().getMessage();
		assertEquals("O nome deve possuir entre 3 e 255 caracteres", message);
	}
	
	@Test
	public void nomeNullTest() {
		Funcionario f = new Funcionario(null, "10054908213", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(1, validar.size());
		String message = validar.iterator().next().getMessage();
		assertEquals("O nome é obrigatório", message);
	}

	@Ignore
	@Test
	public void nomeVazioCpfDuplicadoTest() {
		Funcionario f = new Funcionario("", "06106775001", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(2, validar.size());
		String message;
		message = validar.iterator().next().getMessage();
		assertEquals("O nome deve possuir entre 3 e 255 caracteres", message);
		message = validar.iterator().next().getMessage();
		assertEquals("O CPF já está cadastrado", message);
	}
	
	@Ignore
	@Test
	public void nomeNullCpfDuplicadoTest() {
		Funcionario f = new Funcionario(null, "06106775001", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(2, validar.size());
		String message;
		message = validar.iterator().next().getMessage();
		assertEquals("O nome é obrigatório", message);
		message = validar.iterator().next().getMessage();
		assertEquals("O CPF já está cadastrado", message);
	}

	@Test
	public void nomeVazioCpfInvalidoTest() {
		Funcionario f = new Funcionario("", "21347472828", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(2, validar.size());
		String message;
		Iterator<ConstraintViolation<Object>> iterator = validar.iterator();
		message = iterator.next().getMessage();
		assertEquals("O nome deve possuir entre 3 e 255 caracteres", message);
		message = iterator.next().getMessage();
		assertEquals("O CPF é inválido", message);
	}
	
	@Test
	public void nomeNullCpfInvalidoTest() {
		Funcionario f = new Funcionario(null, "21347472828", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(2, validar.size());
		String message;
		Iterator<ConstraintViolation<Object>> iterator = validar.iterator();
		message = iterator.next().getMessage();
		assertEquals("O nome é obrigatório", message);
		message = iterator.next().getMessage();
		assertEquals("O CPF é inválido", message);
	}

	@Test
	public void cpfVazioTest() {
		Funcionario f = new Funcionario("Leandro", "", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(1, validar.size());
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF é inválido", message);
	}
	
	@Test
	public void cpfNullTest() {
		Funcionario f = new Funcionario("Leandro", null, Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(1, validar.size());
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF é obrigatório", message);
	}

	@Test
	public void nomeVazioCpfVazioTest() {
		Funcionario f = new Funcionario("", "", Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(2, validar.size());
		String message;
		Iterator<ConstraintViolation<Object>> iterator = validar.iterator();
		message = iterator.next().getMessage();
		assertEquals("O nome deve possuir entre 3 e 255 caracteres", message);
		message = iterator.next().getMessage();
		assertEquals("O CPF é inválido", message);
	}
	
	@Test
	public void nomeNullCpfNullTest() {
		Funcionario f = new Funcionario(null, null, Cargo.GERENTE);
		Set<ConstraintViolation<Object>> validar = f.validar();
		assertNotNull(validar);
		assertEquals(2, validar.size());
		String message;
		Iterator<ConstraintViolation<Object>> iterator = validar.iterator();
		message = iterator.next().getMessage();
		assertEquals("O nome é obrigatório", message);
		message = iterator.next().getMessage();
		assertEquals("O CPF é obrigatório", message);
	}

}
