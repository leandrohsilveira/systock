package senai.systock.repository.projection;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Produto;

@Projection(name="produto", types=Produto.class)
public interface ProdutoProjection extends EntidadeBaseProjection {

	String getDescricao();
	
	Integer getQuantidade();
	
	Float getPreco();
	
}
