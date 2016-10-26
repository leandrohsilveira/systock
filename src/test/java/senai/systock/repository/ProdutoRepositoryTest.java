package senai.systock.repository;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import senai.systock.TestApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TestApplication.class)
@AutoConfigureMockMvc
public class ProdutoRepositoryTest extends BaseRepositoryTest {

	@Test
	public void produtoFormAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-form.html").session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void produtoListAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-list.html").session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void produtosResourceAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/produtos").session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void deleteResourcesAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/produtos").session(session))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void searchByDescricaoAnonymousTest() throws Exception {
		String input = "Admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/produtos/search/searchByDescricao").param("descricao", input).session(session))
			.andExpect(status().isUnauthorized());
	}
	
	
	
	
	@Test
	public void produtoFormAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-form.html").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.TEXT_HTML));
	}
	
	@Test
	public void produtoListAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-list.html").session(session))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.TEXT_HTML));
	}
	
	@Test
	public void produtosResourceAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/produtos").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/hal+json;charset=UTF-8"));
	}
	
	@Test
	public void deleteResourcesAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.delete("/produtos").session(session))
		.andExpect(status().isForbidden());
	}

	@Test
	public void searchByDescricaoAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		String input = "%PROD%";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/produtos/search/searchByDescricao").param("descricao", input).session(session))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
			.andReturn();

		String valor = result.getResponse().getContentAsString();
		JSONObject json = new JSONObject(valor);
		assertEquals(1, json.getJSONObject("page").getInt("totalElements"));
	}
	
	
	
	
	
	
	
	@Test
	public void produtoFormGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-form.html").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.TEXT_HTML));
	}
	
	@Test
	public void produtoListGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-list.html").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.TEXT_HTML));
	}
	
	@Test
	public void produtosResourceGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/produtos").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/hal+json;charset=UTF-8"));
	}
	
	@Test
	public void deleteResourcesGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.delete("/produtos").session(session))
		.andExpect(status().isForbidden());
	}

	@Test
	public void searchByDescricaoGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		String input = "%PROD%";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/produtos/search/searchByDescricao").param("descricao", input).session(session))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
				.andReturn();

			String valor = result.getResponse().getContentAsString();
			JSONObject json = new JSONObject(valor);
			assertEquals(1, json.getJSONObject("page").getInt("totalElements"));
	}
	
	
	
	
	
	
	
	@Test
	public void produtoFormVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-form.html").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.TEXT_HTML));
	}

	@Test
	public void produtoListVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/produto/produto-list.html").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.TEXT_HTML));
	}

	@Test
	public void produtosResourceVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/produtos").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/hal+json;charset=UTF-8"));
	}

	@Test
	public void deleteResourcesVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.delete("/produtos").session(session))
		.andExpect(status().isForbidden());
	}

	@Test
	public void searchByDescricaoVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		String input = "%PROD%";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/produtos/search/searchByDescricao").param("descricao", input).session(session))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
				.andReturn();

			String valor = result.getResponse().getContentAsString();
			JSONObject json = new JSONObject(valor);
			assertEquals(1, json.getJSONObject("page").getInt("totalElements"));
	}

}
