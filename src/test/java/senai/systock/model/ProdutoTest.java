package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import senai.systock.exceptions.ValidationException;

public class ProdutoTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Produto p = new Produto("Descricao", 1, 10.50f);
		p.cadastrar();
	}

	@Test
	public void descricaoInexistenteTest() {
		Produto p = new Produto("", 1, 10.50f);
		try {
			p.cadastrar();
			fail("A descricao do produto nao existe.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "Produto sem descricao.");
		}
	}

	@Test
	public void quantidadeInexistenteTest() {
		Produto p = new Produto("Descricao", null, 10.50f);
		try {
			p.cadastrar();
			fail("A quantidade deve ser maior que nada.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "Quantidade nao determinada.");
		}
	}

	@Test
	public void precoInexistenteTest() {
		Produto p = new Produto("Descricao", 1, null);
		try {
			p.cadastrar();
			fail("O valor deve ser maior que nada.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "Valor nao determinado.");
		}
	}

}
