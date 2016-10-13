package senai.systock.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Funcionario;

@Projection(name="funcionario", types=Funcionario.class)
public interface UsuarioFuncionarioInline {
	
	String getLogin();
	
	Funcionario getFuncionario();

}
