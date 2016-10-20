package senai.systock.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.ItemVenda;
import senai.systock.model.Produto;

@Projection(name="itemVenda", types=ItemVenda.class)
public interface ItemVendaProjection extends EntidadeBaseProjection {
	
	public Produto getProduto();

	public Float getValor();

	public Integer getQuantidade();

	public Float getTotal();
	
}
