package senai.systock.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import senai.systock.model.Cliente;
import senai.systock.model.Venda;
import senai.systock.repository.projection.ClienteProjection;
import senai.systock.repository.projection.VendaProjection;

@RepositoryRestResource(collectionResourceRel="clientes" ,path="clientes", excerptProjection=ClienteProjection.class)
public interface ClienteRepository extends PagingAndSortingRepository<Cliente, Long> {

}
