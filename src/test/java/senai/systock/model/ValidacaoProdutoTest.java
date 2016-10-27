package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.junit.Test;

import senai.systock.exceptions.ValidationException;
import senai.systock.util.ConstraintViolationComparator;

public class ValidacaoProdutoTest {

	private static final ConstraintViolationComparator comparator = new ConstraintViolationComparator();

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
		List<ConstraintViolation<Object>> validar = new ArrayList<>(produto.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(2, validar.size());
		assertEquals("A descrição do produto deve possuir entre 3 e 60 caracteres", validar.get(0).getMessage());
		assertEquals("A descrição do produto é obrigatória", validar.get(1).getMessage());
	}

	@Test
	public void quantidadeVaziaTest() {
		Produto produto = new Produto("Samsung Galaxy S7", 4000f, null);
		List<ConstraintViolation<Object>> validar = new ArrayList<>(produto.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("A quantidade de itens do produto é obrigatória", validar.get(0).getMessage());
	}

	@Test
	public void precoVazioTest() {
		Produto p = new Produto("Samsung Galaxy S7", null, 5);
		List<ConstraintViolation<Object>> validar = new ArrayList<>(p.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O preço do produto é obrigatório", validar.get(0).getMessage());
	}

	@Test
	public void precoZeroTest() {
		Produto p = new Produto("Samsung Galaxy S7", 0f, 5);
		List<ConstraintViolation<Object>> validar = new ArrayList<>(p.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O preço do produto não pode ser inferior a 0,01", validar.get(0).getMessage());
	}

	@Test
	public void precoNegativoTest() {
		Produto p = new Produto("Samsung Galaxy S7", -50f, 5);
		List<ConstraintViolation<Object>> validar = new ArrayList<>(p.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("O preço do produto não pode ser inferior a 0,01", validar.get(0).getMessage());
	}

	@Test
	public void quantidadeNegativaTest() {
		Produto p = new Produto("Samsung Galaxy S7", 4000f, -1);
		List<ConstraintViolation<Object>> validar = new ArrayList<>(p.validar());
		Collections.sort(validar, comparator);
		assertNotNull(validar);
		assertEquals(1, validar.size());
		assertEquals("A quantidade de itens do produto não pode ser inferior a zero", validar.get(0).getMessage());
	}

}
