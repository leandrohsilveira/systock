package senai.systock.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Venda;

@Projection(name="venda", types=Venda.class)
public interface VendaProjection extends EntidadeBaseProjection {
		
	Float getSubtotal();
	
	Float getDesconto();
	
	Float getTotal();
	
}
