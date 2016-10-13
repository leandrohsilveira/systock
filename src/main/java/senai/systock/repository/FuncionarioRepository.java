package senai.systock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Funcionario;

@RepositoryRestResource(collectionResourceRel="funcionarios" ,path="funcionarios")
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long> {

}
