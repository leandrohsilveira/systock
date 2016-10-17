package senai.systock.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.validation.ConstraintViolation;

import org.junit.Ignore;
import org.junit.Test;

import senai.systock.util.ConstraintViolationComparator;

public class ValidacaoUsuarioTest {

	private static final ConstraintViolationComparator comparator = new ConstraintViolationComparator();
	
	@Test
	public void sucessoTest() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("leandro", "12345678", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertTrue(validacoes.isEmpty());
	}

	@Test
	public void loginComecaComNumeroTest() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("1leandro", "12345678", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login deve começar com pelo menos um caracter alfabetico e ter entre 3 e 16 caracteres alfanumericos", message);
	}
	
	
	@Test
	public void loginMaior16CaracteresTest() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("leandro1234567890", "12345678", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login deve possuir entre 3 e 16 caracteres", message);
	}
	
	@Test
	public void loginMenor3CaracteresTest() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("le", "12345678", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login deve possuir entre 3 e 16 caracteres", message);
	}
	
	@Test
	public void senhaMenor8CaracteresTest() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("leandro", "1234567", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("A senha deve possuir entre 8 e 32 caracteres", message);
	}
	
	@Test
	public void senhaMaior32CaracteresTest() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("leandro", "123456789012345678901234567890123", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("A senha deve possuir entre 8 e 32 caracteres", message);
	}
	
	@Test
	public void loginVazio1Test() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario(null, "12345678", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login é obrigatório", message);
	}
	
	@Test
	public void senhaVazia1Test() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("leandro", null, new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("A senha é obrigatória", message);
	}
	
	@Test
	public void loginVazio2Test() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("", "12345678", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		assertEquals(3, validacoes.size());
		Iterator<ConstraintViolation<Object>> mensagens = validacoes.iterator();
		String message = mensagens.next().getMessage();
		assertEquals("O login deve começar com pelo menos um caracter alfabetico e ter entre 3 e 16 caracteres alfanumericos", message);
		message = mensagens.next().getMessage();
		assertEquals("O login deve possuir entre 3 e 16 caracteres", message);
		message = mensagens.next().getMessage();
		assertEquals("O login é obrigatório", message);
	}
	
	@Test
	public void senhaVazia2Test() {
		List<ConstraintViolation<Object>> validacoes = new ArrayList<>(new Usuario("leandro", "", new Funcionario("Leandro", "82821408307", Cargo.ADMINISTRADOR)).validar());
		Collections.sort(validacoes, comparator);
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		assertEquals(2, validacoes.size());
		Iterator<ConstraintViolation<Object>> mensagens = validacoes.iterator();
		String message = mensagens.next().getMessage();
		assertEquals("A senha deve possuir entre 8 e 32 caracteres", message);
		message = mensagens.next().getMessage();
		assertEquals("A senha é obrigatória", message);
	}

}
