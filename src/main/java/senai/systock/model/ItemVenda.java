package senai.systock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Formula;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@Table(name="item_venda")
@SequenceGenerator(name="sequence_gen", sequenceName="item_venda_seq", initialValue=1, allocationSize=1)
public class ItemVenda extends EntidadeBase {
	
	public ItemVenda() {
	}
	
	public ItemVenda(Venda venda, Produto produto, Float valor, Integer quantidade) {
		this.venda = venda;
		this.produto = produto;
		this.valor = valor;
		this.quantidade = quantidade;
	}



	@NotNull(message="A venda a qual o item está inclusa é obrigarória")
	@ManyToOne(optional=false)
	@JoinColumn(name="venda_id", nullable=false)
	private Venda venda;
	
	@NotNull(message="O produto referente ao item da venda é obrigatório")
	@ManyToOne(optional=false)
	@JoinColumn(name="produto_id", nullable=false)
	private Produto produto;
	
	@DecimalMax(value="999999999999", inclusive=true, message="O valor do item de venda não pode ser superior a 999.999.999.999")
	@DecimalMin(value="0.01", message="O valor do item de venda não pode ser inferior a 0,01")
	@NotNull(message="O valor por item de venda é obrigatório")
	@Column(name="valor", length=12)
	private Float valor;

	@DecimalMax(value="999999999999", inclusive=true, message="A quantidade de itens não pode ser superior a 999.999.999.999")
	@DecimalMin(value="1", message="A quantidade de itens não pode ser inferior a 1")
	@NotNull(message="A quantidade de itens é obrigatório")
	@Column(name="quantidade", length=12)
	private Integer quantidade;
	
	@Column(name="total")
	private Float total;
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}
	
}
