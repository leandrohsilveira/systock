package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Ignore;
import org.junit.Test;

import senai.systock.exceptions.ValidationException;

@Ignore
public class ProdutoTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Produto p = new Produto("Samsung Galaxy S7", 4000f, 5);
		p.cadastrar();
	}
	
	@Test
	public void sucessoSemEstoqueTest() {
		Produto p = new Produto("Samsung Galaxy S7", 4000f, 0);
		p.cadastrar();
}
	
	@Test
	public void descricaoVaziaTest() {
		Produto p = new Produto("", 4000f, 5);
		try {
			p.cadastrar();
			fail("A descricao do produto nao existe.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O campo Descrição é obrigatório!");
		}
	}

	@Test
	public void quantidadeVaziaTest() {
		Produto p = new Produto("Samsung Galaxy S7", 4000f, null);
		try {
			p.cadastrar();
			fail("A quantidade deve ser maior que nada.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O campo Quantidade é obrigatório!");
		}
	}

	@Test
	public void precoVazioTest() {
		Produto p = new Produto("Samsung Galaxy S7", null, 5);
		try {
			p.cadastrar();
			fail("O valor deve ser maior que nada.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O campo Preço é obrigatório!");
		}
	}
	
		@Test
		public void precoZeroTest() {
			Produto p = new Produto("Samsung Galaxy S7", 0f, 5);
			try {
				p.cadastrar();
				fail("O valor deve ser maior que zero.");
			} catch (ValidationException e) {
				assertEquals(e.getMensagens().length, 1);
				assertEquals(e.getMensagens()[0], "O preço do produto deve ser maior que zero!");
			}
	}
		
		@Test
		public void precoNegativoTest() {
			Produto p = new Produto("Samsung Galaxy S7", -50f, 5);
			try {
				p.cadastrar();
				fail("O valor nao pode ser negativo.");
			} catch (ValidationException e) {
				assertEquals(e.getMensagens().length, 1);
				assertEquals(e.getMensagens()[0], "O preço do produto deve ser maior que zero!");
			}	
		}
		
		@Test
		public void quantidadeNegativaTest() {
			Produto p = new Produto("Samsung Galaxy S7", -50f, -1);
			try {
				p.cadastrar();
				fail("A quantidade nao pode ser negativa.");
			} catch (ValidationException e) {
				assertEquals(e.getMensagens().length, 1);
				assertEquals(e.getMensagens()[0], "O campo Quantidade deve ser maior que zero!");
			}	
		}

}
