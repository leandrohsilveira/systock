package senai.systock.dao;

import org.springframework.data.repository.CrudRepository;

import senai.systock.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Usuario findByLogin(String login);
	
}
