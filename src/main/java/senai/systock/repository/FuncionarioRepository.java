package senai.systock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Funcionario;
import senai.systock.repository.projection.FuncionarioProjection;

@RepositoryRestResource(collectionResourceRel="funcionarios" ,path="funcionarios", excerptProjection=FuncionarioProjection.class)
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long> {

}
