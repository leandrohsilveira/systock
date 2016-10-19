package senai.systock.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Cliente;

@Projection(name="cliente", types=Cliente.class)
public interface ClienteProjection extends EntidadeBaseProjection {

	String getNome();
	
	String getCpf();
	
	String getEmail();
	
}
