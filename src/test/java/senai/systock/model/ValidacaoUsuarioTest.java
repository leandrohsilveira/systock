package senai.systock.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidationException;

import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Test;

public class ValidacaoUsuarioTest {

	@Test
	public void sucessoTest() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("leandro", "12345678").validar();
		assertNotNull(validacoes);
		assertTrue(validacoes.isEmpty());
	}

	@Test
	public void loginComecaComNumeroTest() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("1leandro", "12345678").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login deve começar com pelo menos um caracter alfabetico e ter entre 3 e 16 caracteres alfanumericos", message);
	}
	
	
	@Test
	public void loginMaior16CaracteresTest() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("leandro1234567890", "12345678").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login deve possuir entre 3 e 16 caracteres", message);
	}
	
	@Test
	public void loginMenor3CaracteresTest() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("le", "12345678").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login deve possuir entre 3 e 16 caracteres", message);
	}
	
	@Test
	public void senhaMenor8CaracteresTest() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("leandro", "1234567").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("A senha deve possuir entre 8 e 32 caracteres", message);
	}
	
	@Test
	public void senhaMaior32CaracteresTest() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("leandro", "123456789012345678901234567890123").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("A senha deve possuir entre 8 e 32 caracteres", message);
	}
	
	@Test
	public void loginVazio1Test() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario(null, "12345678").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login é obrigatório", message);
	}
	
	@Test
	public void senhaVazia1Test() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("leandro", null).validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("A senha é obrigatória", message);
	}
	
	@Test
	public void loginVazio2Test() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("", "12345678").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("O login deve possuir entre 3 e 16 caracteres", message);
	}
	
	@Test
	public void senhaVazia2Test() {
		Set<ConstraintViolation<Object>> validacoes = new Usuario("leandro", "").validar();
		assertNotNull(validacoes);
		assertFalse(validacoes.isEmpty());
		String message = validacoes.iterator().next().getMessage();
		assertEquals("A senha deve possuir entre 8 e 32 caracteres", message);
	}

}
