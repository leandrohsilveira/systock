package senai.systock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Cargo;
import senai.systock.model.Funcionario;
import senai.systock.repository.projection.FuncionarioProjection;

@RepositoryRestResource(collectionResourceRel="funcionarios" ,path="funcionarios", excerptProjection=FuncionarioProjection.class)
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long> {

	@Query("select f from Funcionario f where upper(f.nome) like :nome")
	Page<Funcionario> findFuncionarioLike(Pageable pageable, @Param("nome") String nome);
	
	
	@Query("select f from Funcionario f where (:nome is null or upper(f.nome) like :nome) and (:cpf is null or f.cpf = :cpf) and (:cargo is null or f.cargo = :cargo)")
	Page<Funcionario> filter(Pageable pageable, @Param("nome") String nome, @Param("cpf") String cpf, @Param("cargo") Cargo cargo);
	
}
