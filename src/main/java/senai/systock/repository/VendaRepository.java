package senai.systock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.SituacaoVenda;
import senai.systock.model.Venda;
import senai.systock.repository.projection.VendaProjection;

@RepositoryRestResource(collectionResourceRel="vendas" ,path="vendas", excerptProjection=VendaProjection.class)
public interface VendaRepository extends PagingAndSortingRepository<Venda, Long> {
	
	@Query("select v from Venda v left join v.funcionario f left join v.cliente c "
			+ "where (:cliente is null or (upper(c.nome) like concat('%', upper(:cliente), '%') or c.cpf = :funcionario or c.email = :funcionario)) "
			+ "and (:funcionario is null or (upper(f.nome) like  concat('%', upper(:funcionario), '%') or f.cpf = :funcionario)) "
			+ "and (:situacao is null or v.situacao = :situacao)")
	Page<Venda> filter(Pageable pageable, @Param("cliente") String cliente, @Param("funcionario") String funcionario, @Param("situacao") SituacaoVenda situacao);
	
}
