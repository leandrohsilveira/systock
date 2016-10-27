package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

import org.junit.Test;

public class ValidacaoClienteTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Cliente cliente = new Cliente("Leandro", "76042714210", "leandro@gmail.com");
		Set<ConstraintViolation<Object>> validacoes = cliente.validar();
		assertNotNull(validacoes);
		assertTrue(validacoes.isEmpty());
	}

	@Test
	public void cpfInvalidoTest() {
		Cliente cliente = new Cliente("Leandro 1", "29697822941", "leandro1@gmail.com");
		Set<ConstraintViolation<Object>> validar = cliente.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF é inválido", message);
	}

	@Test
	public void emailInvalidoTest1() {
		Cliente cliente = new Cliente("Leandro 1", "55587135853", "leandro@");
		Set<ConstraintViolation<Object>> validar = cliente.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O E-mail é inválido", message);
	}

	@Test
	public void emailInvalidoTest2() {
		Cliente cliente = new Cliente("Leandro 1", "55587135853", "@gmail.com");
		Set<ConstraintViolation<Object>> validar = cliente.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O E-mail é inválido", message);
	}

	@Test
	public void nomeVazioTest() {
		Cliente cliente = new Cliente(null, "55587135853", "leandro1@gmail.com");
		Set<ConstraintViolation<Object>> validar = cliente.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O Nome é obrigatório", message);
	}

	@Test
	public void cpfVazioTest() {
		Cliente cliente = new Cliente("Leandro 1", null, "leandro1@gmail.com");
		Set<ConstraintViolation<Object>> validar = cliente.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O CPF é obrigatório", message);
	}

	@Test
	public void emailVazioTest() {
		Cliente cliente = new Cliente("Leandro 1", "55587135853", null);
		Set<ConstraintViolation<Object>> validar = cliente.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O E-mail é obrigatório", message);
	}

}
