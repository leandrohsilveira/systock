package senai.systock.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Cargo;
import senai.systock.model.Funcionario;

@Projection(name="funcionario", types=Funcionario.class)
public interface FuncionarioProjection extends EntidadeBaseProjection {
	
	String getNome();
	
	String getCpf();
	
	Cargo getCargo();
	
}
