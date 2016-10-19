package senai.systock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Cliente;
import senai.systock.repository.projection.ClienteProjection;

@RepositoryRestResource(collectionResourceRel="clientes" ,path="clientes", excerptProjection=ClienteProjection.class)
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {
	
	@Query("select c from Cliente c where upper(c.nome) like :nome")
	Page<Cliente> findClienteLike(Pageable pageable, @Param("nome") String nome);
	
	
}
