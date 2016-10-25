package senai.systock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Cargo;
import senai.systock.model.Usuario;
import senai.systock.repository.projection.UsuarioFuncionarioInline;

@RepositoryRestResource(collectionResourceRel = "usuarios", path = "usuarios", excerptProjection = UsuarioFuncionarioInline.class)
public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

	Usuario findByLogin(@Param("login") String login);

	@Query("select u from Usuario as u join u.funcionario as f where (:login is null or u.login = :login) and (:nome is null or upper(f.nome) like concat('%', upper(:nome), '%')) and (:cpf is null or f.cpf = REGEXP_REPLACE(:cpf, '(\\.|\\-)', '')) and (:cargo is null or f.cargo = :cargo)")
	Page<Usuario> filter(Pageable pageable, @Param("login") String login, @Param("nome") String nome, @Param("cpf") String cpf, @Param("cargo") Cargo cargo);
	
	@Query("select count(u.id) > 0 from Usuario u where u.login = :value and (:except is null or u.login <> :except)")
	boolean unique(@Param("value") String value, @Param("except") String except);

}
