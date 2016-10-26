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
import senai.systock.model.Cargo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=TestApplication.class)
@AutoConfigureMockMvc
public class UsuarioRepositoryTest extends BaseRepositoryTest {

	@Test
	public void usuarioFormAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-form.html").session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void usuarioListAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-list.html").session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void usuariosResourceAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/usuarios").session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void deleteResourcesAnonymousTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.delete("/usuarios").session(session))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void findByLoginAnonymousTest() throws Exception {
		String input = "admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void findByLoginNotFoundAnonymousTest() throws Exception {
		String input = "adminx";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void filterLoginAnonymousTest() throws Exception {
		String input = "admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("login", input).session(session))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void filterNomeAnonymousTest() throws Exception {
		String input = "Admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("nome", input).session(session))
			.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void filterCpfAnonymousTest() throws Exception {
		String input = "94149790507";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cpf", input).session(session))
		.andExpect(status().isUnauthorized());
	}
	
	@Test
	public void filterCargoAnonymousTest() throws Exception {
		String input = Cargo.ADMINISTRADOR.name();
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cargo", input).session(session))
		.andExpect(status().isUnauthorized());
	}
	
	
	
	
	@Test
	public void usuarioFormAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-form.html").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.TEXT_HTML));
	}
	
	@Test
	public void usuarioListAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-list.html").session(session))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.TEXT_HTML));
	}
	
	@Test
	public void usuariosResourceAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios").session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/hal+json;charset=UTF-8"));
	}
	
	@Test
	public void deleteResourcesAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.delete("/usuarios").session(session))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void findByLoginAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		String input = "admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/hal+json;charset=UTF-8"));
		
	}
	
	@Test
	public void findByLoginNotFoundAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		
		String input = "adminx";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void filterLoginAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		String input = "admin";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("login", input).session(session))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
			.andReturn();
		
		String valor = result.getResponse().getContentAsString();
		JSONObject json = new JSONObject(valor);
		assertEquals(1, json.getJSONObject("page").getInt("totalElements"));
		assertEquals(input, json.getJSONObject("_embedded").getJSONArray("usuarios").getJSONObject(0).getString("login"));
	}

	@Test
	public void filterNomeAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		String input = "%ADMIN%";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("nome", input).session(session))
			.andExpect(status().isOk())
			.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
			.andReturn();

		String valor = result.getResponse().getContentAsString();
		JSONObject json = new JSONObject(valor);
		assertEquals(1, json.getJSONObject("page").getInt("totalElements"));
	}

	@Test
	public void filterCpfAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		String input = "94149790507";
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cpf", input).session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
		.andReturn();

		String valor = result.getResponse().getContentAsString();
		JSONObject json = new JSONObject(valor);
		assertEquals(1, json.getJSONObject("page").getInt("totalElements"));
	}

	@Test
	public void filterCargoAdminTest() throws Exception {
		autenticarOk("admin", "12345678");
		String input = Cargo.ADMINISTRADOR.name();
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cargo", input).session(session))
		.andExpect(status().isOk())
		.andExpect(content().contentType("application/hal+json;charset=UTF-8"))
		.andReturn();

		String valor = result.getResponse().getContentAsString();
		JSONObject json = new JSONObject(valor);
		assertEquals(1, json.getJSONObject("page").getInt("totalElements"));
	}
	
	
	
	
	
	
	
	@Test
	public void usuarioFormGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-form.html").session(session))
			.andExpect(status().isForbidden());
	}
	
	@Test
	public void usuarioListGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-list.html").session(session))
			.andExpect(status().isForbidden());
	}
	
	@Test
	public void usuariosResourceGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios").session(session))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void deleteResourcesGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.delete("/usuarios").session(session))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void findByLoginGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		String input = "admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
			.andExpect(status().isForbidden());
		
	}
	
	@Test
	public void findByLoginNotFoundGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		
		String input = "adminx";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void filterLoginGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		String input = "admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("login", input).session(session))
			.andExpect(status().isForbidden());
	}

	@Test
	public void filterNomeGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		String input = "Admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("nome", input).session(session))
			.andExpect(status().isForbidden());
	}

	@Test
	public void filterCpfGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		String input = "94149790507";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cpf", input).session(session))
		.andExpect(status().isForbidden());
	}

	@Test
	public void filterCargoGerenteTest() throws Exception {
		autenticarOk("gerente", "12345678");
		String input = Cargo.ADMINISTRADOR.name();
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cargo", input).session(session))
		.andExpect(status().isForbidden());
	}
	
	
	
	
	
	
	
	@Test
	public void usuarioFormVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-form.html").session(session))
			.andExpect(status().isForbidden());
	}

	@Test
	public void usuarioListVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/paginas/usuario/usuario-list.html").session(session))
			.andExpect(status().isForbidden());
	}

	@Test
	public void usuariosResourceVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios").session(session))
		.andExpect(status().isForbidden());
	}

	@Test
	public void deleteResourcesVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		mvc.perform(MockMvcRequestBuilders.delete("/usuarios").session(session))
		.andExpect(status().isForbidden());
	}

	@Test
	public void findByLoginVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		String input = "admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
			.andExpect(status().isForbidden());
		
	}

	@Test
	public void findByLoginNotFoundVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		
		String input = "adminx";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/findByLogin").param("login", input).session(session))
		.andExpect(status().isForbidden());
	}
	
	@Test
	public void filterLoginVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		String input = "admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("login", input).session(session))
			.andExpect(status().isForbidden());
	}

	@Test
	public void filterNomeVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		String input = "Admin";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("nome", input).session(session))
			.andExpect(status().isForbidden());
	}

	@Test
	public void filterCpfVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		String input = "94149790507";
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cpf", input).session(session))
		.andExpect(status().isForbidden());
	}

	@Test
	public void filterCargoVendedorTest() throws Exception {
		autenticarOk("vendedor", "12345678");
		String input = Cargo.ADMINISTRADOR.name();
		
		mvc.perform(MockMvcRequestBuilders.get("/usuarios/search/filter").param("cargo", input).session(session))
		.andExpect(status().isForbidden());
	}
	
}
