package senai.systock.controller;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import senai.systock.TestApplication;
import senai.systock.repository.BaseRepositoryTest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TestApplication.class)
@AutoConfigureMockMvc
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
	public void loginVazioTest() throws Exception {
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
	public void loginSenhaVaziosTest() throws Exception {
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
	public void loginIncorretoTest() throws Exception {
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
	public void loginSenhaIncorretosTest() throws Exception {
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
	public void getAuthTest() throws Exception {
		authenticate("admin", "12345678");
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
	public void getAuthQuandoNaoAutenticadoTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/auth").session(session)).andExpect(status().isNoContent());
	}
	
	@Test
	public void logoutTest() throws Exception {
		authenticate("admin", "12345678");
		mvc.perform(MockMvcRequestBuilders.get("/logout").session(session)).andExpect(status().isOk());
		mvc.perform(MockMvcRequestBuilders.get("/auth").session(session)).andExpect(status().isNoContent());
	}
	
}
