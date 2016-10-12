package senai.systock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

import senai.systock.model.EntidadeBase.Public;

@Entity
@Table(name="funcionario")
@SequenceGenerator(name="sequence_gen", sequenceName="funcionario_seq", initialValue=1, allocationSize=1)
public class Funcionario extends EntidadeBase {
	
	public Funcionario() {
	}
	
	
	public Funcionario(String nome, String cpf, String cargo) {
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
	}
	
	@NotNull
	@JsonView(Public.class)
	@Column(name="nome", nullable=false)
	private String nome;
	
	@NotNull
	@Column(name="cpf", nullable=false)
	private String cpf;
	
	@NotNull
	@JsonView(Public.class)
	@Column(name="cargo", nullable=false)
	private String cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}