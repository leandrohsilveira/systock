package senai.systock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="produto")
@SequenceGenerator(name="sequence_gen", sequenceName="produto_seq", initialValue=1, allocationSize=1)
public class Produto extends EntidadeBase {
	
	@NotBlank(message="A descrição do produto é obrigatória")
	@Size(min=3, max=60, message="A descrição do produto deve possuir entre 3 e 60 caracteres")
	@Column(name="descricao", length=60, nullable=false)
	private String descricao;
	
	@NotNull(message="A quantidade de itens do produto é obrigatória")
	@DecimalMax(value="999999999999", inclusive=true, message="A quantidade de itens do produto não pode ser superior a 999.999.999.999")
	@DecimalMin(value="0", inclusive=true, message="A quantidade de itens do produto não pode ser inferior a zero")
	private Integer quantidade;
	
	@NotNull(message="O preço do produto é obrigatório")
	@DecimalMax(value="999999999999", inclusive=true, message="O preço do produto não pode ser superior a 999.999.999.999")
	@DecimalMin(value="0.01", inclusive=true, message="O preço do produto não pode ser inferior a 0,01")
	private Float preco;
	
	public Produto() {
		super();
	}
	
	public Produto(String descricao, Float preco, Integer quantidade) {
		this();
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Float getPreco() {
		return preco;
	}

	public void setPreco(Float preco) {
		this.preco = preco;
	}

}
