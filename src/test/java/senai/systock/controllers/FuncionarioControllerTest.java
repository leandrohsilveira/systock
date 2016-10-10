package senai.systock.controllers;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import senai.systock.model.Funcionario;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FuncionarioControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void funcionarioTest() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/funcionario")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content()
							.json(new JSONObject(new Funcionario("Leandro", "12345678901", "Administrador")).toString()));
	}
	
	@Test
	@Ignore
	public void ivanTest() throws Exception {
		mvc.perform(
				MockMvcRequestBuilders
				.get("/funcionario")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(
						content()
						.json(new JSONObject(new Funcionario("Ivan", "12345678901", "Administrador")).toString()));
	}

}