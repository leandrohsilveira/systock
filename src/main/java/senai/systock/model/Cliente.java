package senai.systock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "sequence_gen", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
public class Cliente extends EntidadeBase {
	
	public Cliente(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	@NotBlank
	@Size(min=3, max=255)
	@Column(name="nome", nullable=false)
	private String nome;
	
	@NotBlank
	private String cpf;
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
