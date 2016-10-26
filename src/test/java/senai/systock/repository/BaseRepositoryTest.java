package senai.systock.repository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BaseRepositoryTest {

	@Autowired
	protected MockMvc mvc;
	
	@Autowired
	protected MockHttpSession session;
		
	protected void autenticarOk(String login, String senha) throws Exception {
		autenticar(login, senha).andExpect(status().isOk());
	}

	protected ResultActions autenticar(String login, String senha) throws Exception {
		return mvc.perform(MockMvcRequestBuilders.post("/auth")
										.with(csrf().asHeader())
										.param("login", login)
										.param("senha", senha)
										.contentType(MediaType.APPLICATION_FORM_URLENCODED)
										.session(session));
	}
	
}
