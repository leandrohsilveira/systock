package senai.systock.repository.projection;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import senai.systock.model.Cliente;
import senai.systock.model.Funcionario;
import senai.systock.model.SituacaoVenda;
import senai.systock.model.Venda;

@Projection(name="venda", types=Venda.class)
public interface VendaProjection extends EntidadeBaseProjection {
	
	Cliente getCliente();
	
	Funcionario getFuncionario();
	
	Float getSubtotal();
	
	Float getDesconto();
	
	Float getTotal();
	
	SituacaoVenda getSituacao();
	
	Date getDataCriacao();
	
	Date getDataConclusao();
	
}
