package senai.systock.repository.projection;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Cliente;
import senai.systock.model.Funcionario;
import senai.systock.model.ItemVenda;
import senai.systock.model.Venda;

@Projection(name="vendaCompleta", types=Venda.class)
public interface VendaCompletaProjection extends VendaProjection {
	
	List<ItemVenda> getItens();
	
}
