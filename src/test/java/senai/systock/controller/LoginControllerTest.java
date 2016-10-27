package senai.systock.controller;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import senai.systock.TestApplication;
import senai.systock.repository.BaseRepositoryTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TestApplication.class)
@AutoConfigureMockMvc
@Ignore
public class LoginControllerTest extends BaseRepositoryTest {
	
	@Test
	public void saveCsrfTokenTest() throws Exception {
		String csrfToken = mvc.perform(MockMvcRequestBuilders.head("/").session(session))
			.andExpect(status().isOk())
			.andReturn().getResponse().getContentAsString();
		assertNotNull(csrfToken);
	}
	
	@Test
	public void loginTest() throws Exception {
		String login = "admin";
		String senha = "12345678";
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.with(csrf().asHeader())
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
			).andExpect(status().isOk());
	}
	
	@Test
	public void loginSemCsrfTokenTest() throws Exception {
		String login = "admin";
		String senha = "12345678";
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
				).andExpect(status().isForbidden());
	}

	@Test
	public void loginComLoginVazioTest() throws Exception {
		String login = null;
		String senha = "12345678";
		
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.with(csrf().asHeader())
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
			).andExpect(status().isBadRequest());
	}
	
	@Test
	public void senhaVaziaTest() throws Exception {
		String login = "admin";
		String senha = null;
		
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.with(csrf().asHeader())
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
			).andExpect(status().isBadRequest());
	}
	
	@Test
	public void loginComLoginSenhaVaziosTest() throws Exception {
		String login = null;
		String senha = null;
		
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.with(csrf().asHeader())
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
			).andExpect(status().isBadRequest());
	}
	
	@Test
	public void loginComLoginIncorretoTest() throws Exception {
		String login = "adminnnn";
		String senha = "12345678";
		
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.with(csrf().asHeader())
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
			).andExpect(status().isForbidden());
	}
	
	@Test
	public void senhaIncorretaTest() throws Exception {
		String login = "admin";
		String senha = "1234";
		
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.with(csrf().asHeader())
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
			).andExpect(status().isForbidden());
	}
	
	@Test
	public void loginComLoginSenhaIncorretosTest() throws Exception {
		String login = "adminnnnnn";
		String senha = "1234";
		
		mvc.perform(MockMvcRequestBuilders.post("/auth")
				.with(csrf().asHeader())
				.param("login", login)
				.param("senha", senha)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED)
				.session(session)
			).andExpect(status().isForbidden());
	}
	
	@Test
	public void getUsuarioAutenticadoTest() throws Exception {
		autenticarOk("admin", "12345678");
		String responseString = mvc.perform(MockMvcRequestBuilders.get("/auth").session(session))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse().getContentAsString();
		
		JSONObject usuarioJsonObject = new JSONObject(responseString);
		assertNotNull(responseString);
		assertNotNull(usuarioJsonObject);
		assertEquals("admin", usuarioJsonObject.get("login"));
		
		JSONObject funcionarioJsonObject = usuarioJsonObject.getJSONObject("funcionario");
		assertNotNull(funcionarioJsonObject);
		assertEquals("Administrador do Sistema", funcionarioJsonObject.get("nome"));
	}
	
	@Test
	public void getUsuarioAutenticadoQuandoNaoAutenticadoTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/auth").session(session)).andExpect(status().isNoContent());
	}
	
	@Test
	public void logoutTest() throws Exception {
		autenticarOk("admin", "12345678");
		mvc.perform(MockMvcRequestBuilders.get("/logout").session(session)).andExpect(status().isOk());
		mvc.perform(MockMvcRequestBuilders.get("/auth").session(session)).andExpect(status().isNoContent());
	}
	
	@Test
	public void logoutQuandoNaoAutenticadoTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/logout").session(session)).andExpect(status().isOk());
	}
	
	@Test
	@DirtiesContext
	public void alterarUsuarioAutenticadoAlterandoSenhaTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		mvc.perform(MockMvcRequestBuilders.put("/auth").session(session)
				.with(csrf().asHeader())
				.param("alterarSenha", "true")
				.param("senhaAtual", "12345678")
				.param("novaSenha", "87654321")
				.param("nome", "Vendedor Editado")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isOk());
		
		mvc.perform(MockMvcRequestBuilders.get("/logout").session(session)).andExpect(status().isOk());
		
		autenticar("vendedor", "12345678").andExpect(status().isForbidden());
		autenticar("vendedor", "87654321").andExpect(status().isOk());
		
		String responseString = mvc.perform(MockMvcRequestBuilders.get("/auth").session(session))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8)).andReturn().getResponse().getContentAsString();
		
		JSONObject usuarioJsonObject = new JSONObject(responseString);
		assertNotNull(responseString);
		assertNotNull(usuarioJsonObject);
		assertEquals("vendedor", usuarioJsonObject.get("login"));
		
		JSONObject funcionarioJsonObject = usuarioJsonObject.getJSONObject("funcionario");
		assertNotNull(funcionarioJsonObject);
		assertEquals("Vendedor Editado", funcionarioJsonObject.get("nome"));
	}
	
	@Test
	public void alterarUsuarioAutenticadoSemCsrfTokenTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		mvc.perform(MockMvcRequestBuilders.put("/auth").session(session)
				.param("alterarSenha", "true")
				.param("senhaAtual", "12345678")
				.param("novaSenha", "87654321")
				.param("nome", "Vendedor Editado")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void alterarUsuarioAutenticadoAlterandoSenhaComSenhaAtualIncorretaTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		mvc.perform(MockMvcRequestBuilders.put("/auth").session(session)
				.with(csrf().asHeader())
				.param("alterarSenha", "true")
				.param("senhaAtual", "123")
				.param("novaSenha", "87654321")
				.param("nome", "Vendedor Editado").contentType(MediaType.APPLICATION_FORM_URLENCODED))
			.andExpect(status().isForbidden());
	}
	
	@Test
	public void alterarUsuarioAutenticadoAlterandoSenhaComNomeInvalidoTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		mvc.perform(MockMvcRequestBuilders.put("/auth").session(session)
				.with(csrf().asHeader())
				.param("nome", "Ve").contentType(MediaType.APPLICATION_FORM_URLENCODED))
		.andExpect(status().isBadRequest());
	}
	
}
