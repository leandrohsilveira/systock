package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Ignore;
import org.junit.Test;

import senai.systock.exceptions.ValidationException;

@Ignore
public class ValidacaoProdutoTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Produto produto = new Produto("Samsung Galaxy S7", 4000f, 5);
		Set<ConstraintViolation<Object>> validar = produto.validar();
		assertNotNull(validar);
		assertTrue(validar.isEmpty());
	}

	@Test
	public void sucessoSemEstoqueTest() {
		Produto produto = new Produto("Samsung Galaxy S7", 4000f, 0);
		Set<ConstraintViolation<Object>> validar = produto.validar();
		assertNotNull(validar);
		assertTrue(validar.isEmpty());
	}

	@Test
	public void descricaoVaziaTest() {
		Produto produto = new Produto("", 4000f, 5);
		Set<ConstraintViolation<Object>> validar = produto.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("A descrição não pode estar vazia", message);
	}

	@Test
	public void quantidadeVaziaTest() {
		Produto produto = new Produto("Samsung Galaxy S7", 4000f, null);
		Set<ConstraintViolation<Object>> validar = produto.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("A quantidade deve ser preenchida", message);
	}

	@Test
	public void precoVazioTest() {
		Produto p = new Produto("Samsung Galaxy S7", null, 5);
		Set<ConstraintViolation<Object>> validar = p.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O preço não pode estar vazio", message);
	}

	@Test
	public void precoZeroTest() {
		Produto p = new Produto("Samsung Galaxy S7", 0f, 5);
		Set<ConstraintViolation<Object>> validar = p.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O preço deve ser maior que 0", message);
	}

	@Test
	public void precoNegativoTest() {
		Produto p = new Produto("Samsung Galaxy S7", -50f, 5);
		Set<ConstraintViolation<Object>> validar = p.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("O preço deve ser maior que 0", message);
	}

	@Test
	public void quantidadeNegativaTest() {
		Produto p = new Produto("Samsung Galaxy S7", 4000f, -1);
		
		Set<ConstraintViolation<Object>> validar = p.validar();
		assertNotNull(validar);
		assertTrue(validar.size() == 1);
		String message = validar.iterator().next().getMessage();
		assertEquals("A quantidade deve ser maior que 0", message);
	}

}
