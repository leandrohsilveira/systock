package senai.systock.model;

import org.junit.Test;

import senai.systock.exceptions.ValidationException;

import static org.junit.Assert.*;

public class LoginTest {

	@Test
	public void sucessoTest() throws ValidationException {
		Login l = new Login("teste", "123456");
		l.login();
	}

	@Test
	public void usuarioInexistenteTest() {
		Login l = new Login("Teste", "123456");
		try {
			l.login();
			fail("O usuário nao existe.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0],
					"Usuário e/ou senha incorreto(s).");
		}
	}

	@Test
	public void senhaIncorretaTeste() {
		Login l = new Login("teste", "1234567");
		try {
			l.login();
			fail("A senha esta incorreta.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0],
					"Usuário e/ou senha incorreto(s).");
		}
	}

	@Test
	public void usuarioIncorretosenhaIncorretoTest() {
		Login l = new Login("Teste", "1234567");
		try {
			l.login();
			fail("O campo usuário esta incorreto. \n O campo senha esta incorreto.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0],
					"Usuário e/ou senha incorreto(s).");
		}
	}

	@Test
	public void usuarioObrigatorioTest() {
		Login l = new Login("", "123456");
		try {
			l.login();
			fail("O campo usuário e obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O campo de login é obrigatório.");
		}
	}

	@Test
	public void senhaObrigatoriaTest() {
		Login l = new Login("teste", "");
		try {
			l.login();
			fail("O campo senha e obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 1);
			assertEquals(e.getMensagens()[0], "O campo de senha é obrigatório.");
		}
	}

	@Test
	public void usuarioObrigatoriosenhaObrigatorioTest() {
		Login l = new Login("", "");
		try {
			l.login();
			fail("O campo usuário e obrigatorio. \n O campo senha e obrigatorio.");
		} catch (ValidationException e) {
			assertEquals(e.getMensagens().length, 2);
			assertEquals(e.getMensagens()[0], "O campo de login é obrigatório.");
			assertEquals(e.getMensagens()[1], "O campo de senha é obrigatório.");

		}
	}

}
