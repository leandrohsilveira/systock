package senai.systock.dao;

import org.springframework.data.repository.CrudRepository;

import senai.systock.model.Funcionario;

public interface FuncionarioRepository extends CrudRepository<Funcionario, Long> {

}
