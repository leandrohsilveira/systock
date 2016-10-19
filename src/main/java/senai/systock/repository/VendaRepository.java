package senai.systock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Venda;
import senai.systock.repository.projection.VendaProjection;

@RepositoryRestResource(collectionResourceRel="vendas" ,path="vendas", excerptProjection=VendaProjection.class)
public interface VendaRepository extends PagingAndSortingRepository<Venda, Long> {

}
