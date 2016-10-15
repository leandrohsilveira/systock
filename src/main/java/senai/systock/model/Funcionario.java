package senai.systock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name="funcionario")
@SequenceGenerator(name="sequence_gen", sequenceName="funcionario_seq", initialValue=1, allocationSize=1)
public class Funcionario extends EntidadeBase {
	
	public Funcionario() {
	}
	
	
	public Funcionario(String nome, String cpf, Cargo cargo) {
		setAtivo(true);
		this.nome = nome;
		this.cpf = cpf;
		this.cargo = cargo;
	}
	
	@NotNull(message="O nome é obrigatório")
	@Size(min=3, max=255, message="O nome deve possuir entre 3 e 255 caracteres")
	@Column(name="nome", nullable=false)
	private String nome;
	
	@NotNull(message="O CPF é obrigatório")
	@CPF(message="O CPF é inválido")
	@Column(name="cpf", nullable=false)
	private String cpf;
	
	@NotNull(message="O cargo não pode ser nulo")
	@Column(name="cargo", nullable=false)
	@Enumerated(EnumType.STRING)
	private Cargo cargo;

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

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
}