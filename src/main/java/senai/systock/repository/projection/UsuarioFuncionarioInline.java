package senai.systock.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Funcionario;
import senai.systock.model.Usuario;

@Projection(name="usuario", types=Usuario.class)
public interface UsuarioFuncionarioInline extends EntidadeBaseProjection {
	
	String getLogin();
	
	String getSenha();
	
	Funcionario getFuncionario();

}
