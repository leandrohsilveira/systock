package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

import org.junit.Test;

import senai.systock.util.ConstraintViolationComparator;

public class ValidacaoClienteTest {

	private static final ConstraintViolationComparator comparator = new ConstraintViolationComparator();

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
		List<ConstraintViolation<Object>> validar = new ArrayList<>(cliente.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O CPF é inválido", validar.get(0).getMessage());
	}

	@Test
	public void emailInvalidoTest1() {
		Cliente cliente = new Cliente("Leandro 1", "55587135853", "leandro@");
		List<ConstraintViolation<Object>> validar = new ArrayList<>(cliente.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O e-mail é inválido", validar.get(0).getMessage());
	}

	@Test
	public void emailInvalidoTest2() {
		Cliente cliente = new Cliente("Leandro 1", "55587135853", "@gmail.com");
		List<ConstraintViolation<Object>> validar = new ArrayList<>(cliente.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O e-mail é inválido", validar.get(0).getMessage());
	}

	@Test
	public void nomeVazioTest() {
		Cliente cliente = new Cliente("", "55587135853", "leandro1@gmail.com");
		List<ConstraintViolation<Object>> validar = new ArrayList<>(cliente.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(2, validar.size());
		assertEquals("O nome deve possuir entre 3 e 80 caracteres", validar.get(0).getMessage());
		assertEquals("O nome é obrigatório", validar.get(1).getMessage());
	}

	@Test
	public void cpfVazioTest() {
		Cliente cliente = new Cliente("Leandro 1", null, "leandro1@gmail.com");
		List<ConstraintViolation<Object>> validar = new ArrayList<>(cliente.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O CPF é obrigatório", validar.get(0).getMessage());
	}

	@Test
	public void emailVazioTest() {
		Cliente cliente = new Cliente("Leandro 1", "55587135853", null);
		List<ConstraintViolation<Object>> validar = new ArrayList<>(cliente.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O e-mail é obrigatório", validar.get(0).getMessage());
	}

}
