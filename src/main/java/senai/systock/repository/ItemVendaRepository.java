package senai.systock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.ItemVenda;
import senai.systock.repository.projection.ItemVendaProjection;

@RepositoryRestResource(path="itens", collectionResourceRel="itens", excerptProjection=ItemVendaProjection.class)
public interface ItemVendaRepository extends PagingAndSortingRepository<ItemVenda, Long> {
	
}
