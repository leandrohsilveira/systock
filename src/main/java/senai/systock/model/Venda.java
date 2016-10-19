package senai.systock.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnTransformer;
import org.hibernate.annotations.Formula;

@Entity
@Table(name="venda")
@SequenceGenerator(name="sequence_gen", sequenceName="venda_seq", initialValue=1, allocationSize=1)
public class Venda extends EntidadeBase {
	
	public Venda() {
	}
	
	public Venda(Funcionario funcionario, SituacaoVenda situacao) {
		this.funcionario = funcionario;
		this.situacao = situacao;
	}

	@NotNull(message="O funcionário responsável pela venda é obrigatório")
	@ManyToOne(optional=false)
	@JoinColumn(name="funcionario_id", nullable=false)
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name="cliente_id", nullable=true)
	private Cliente cliente;
	
	@OneToMany(mappedBy="venda", cascade={ CascadeType.ALL })
	private List<ItemVenda> itens;
	
	@Column(name="subtotal")
	private Float subtotal = 0f;
	
	@Column(name="desconto")
	private Float desconto = 0f;
	
	@Column(name="total")
	private Float total = 0f;
	
	@NotNull(message="A situação da venda não pode ser nula")
	@Column(name="situacao", length=9, nullable=false)
	private SituacaoVenda situacao;
	
	@PrePersist
	private void prePersist() {
		calcularTotais();
	}
	
	@PreUpdate
	private void preUpdate() {
		calcularTotais();
	}
	
	private void calcularTotais() {
		this.subtotal = 0f;
		if(itens != null) {
			for (ItemVenda itemVenda : itens) {
				float totalItem = itemVenda.getValor() * itemVenda.getQuantidade();
				itemVenda.setTotal(totalItem);
				this.subtotal += totalItem;
			}
		}
		this.total = this.subtotal - (this.subtotal * (this.desconto / 100));
	}
	
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemVenda> getItens() {
		return itens;
	}

	public void setItens(List<ItemVenda> itens) {
		this.itens = itens;
	}

	public Float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Float subtotal) {
		this.subtotal = subtotal;
	}

	public Float getDesconto() {
		return desconto;
	}

	public void setDesconto(Float desconto) {
		this.desconto = desconto;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	public SituacaoVenda getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoVenda situacao) {
		this.situacao = situacao;
	}

	@Transient
	public void addItemVenda(ItemVenda itemVenda) {
		if(this.itens == null) this.itens = new ArrayList<>();
		itens.add(itemVenda);
	}
	
	
}
