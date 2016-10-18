package senai.systock.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Produto;
import senai.systock.repository.projection.ProdutoProjection;

@RepositoryRestResource(path="produtos", collectionResourceRel="produtos", excerptProjection=ProdutoProjection.class)
public interface ProdutoRepository extends PagingAndSortingRepository<Produto, Long> {
	
	@Query("select p from Produto p where upper(p.descricao) like :descricao")
	Page<Produto> searchByDescricao(Pageable pageable, @Param("descricao") String descricao);
	
}
