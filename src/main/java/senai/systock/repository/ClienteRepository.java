package senai.systock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Cliente;
import senai.systock.repository.projection.ClienteProjection;

@RepositoryRestResource(collectionResourceRel = "clientes", path = "clientes", excerptProjection = ClienteProjection.class)
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

	@Query("select c from Cliente c where upper(c.nome) like :nome")
	Page<Cliente> findClienteLike(Pageable pageable, @Param("nome") String nome);

	@Query("select c from Cliente c where (:query is null or (upper(c.nome) like concat('%', upper(:query), '%') or c.cpf = REGEXP_REPLACE(:query, '(\\.|\\-)', ''))) and c.ativo is true")
	Page<Cliente> query(Pageable pageable, @Param("query") String query);

	@Query("select c from Cliente as c where (:nome is null or upper(c.nome) like concat('%', upper(:nome), '%')) and (:cpf is null or c.cpf = REGEXP_REPLACE(:cpf, '(\\.|\\-)', '')) and (:email is null or upper(c.email) like concat('%', upper(:email), '%'))")
	Page<Cliente> filter(Pageable pageable, @Param("nome") String nome, @Param("cpf") String cpf, @Param("email") String email);

	@Query("select count(c.id) > 0 from Cliente c where c.cpf = REGEXP_REPLACE(:value, '(\\.|\\-)', '') and (:except is null or c.cpf <> :except)")
	boolean unique(@Param("value") String value, @Param("except") String except);

	@Query("select count(c.id) > 0 from Cliente c where c.email = :value and (:except is null or c.email <> :except)")
	boolean uniqueEmail(@Param("value") String value, @Param("except") String except);

}
