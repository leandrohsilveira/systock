package senai.systock.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="produto")
@SequenceGenerator(name="sequence_gen", sequenceName="produto_seq", initialValue=1, allocationSize=1)
public class Produto extends EntidadeBase {

	@NotBlank(message="A descrição não pode estar vazia")
	@Size(message="A descrição não pode estar vazia")
    @Column(name="descricao", nullable=false)
	private String descricao;
	
	@Column(name="quantidade", nullable=false)	
	@NotNull(message =  "A quantidade deve ser preenchida")
	@DecimalMin(value = "0", message = "A quantidade deve ser maior que 0")
	private Integer quantidade;
	
	@Column(name="preco", nullable=false)
	@NotNull(message =  "O preço não pode estar vazio")
	@DecimalMin(value = "0.1", message = "O preço deve ser maior que 0")
	private Float preco;

	public Produto(String descricao, Float preco, Integer quantidade) {
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
