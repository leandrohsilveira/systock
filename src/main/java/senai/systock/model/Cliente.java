package senai.systock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "cliente")
@SequenceGenerator(name = "sequence_gen", sequenceName = "cliente_seq", initialValue = 1, allocationSize = 1)
public class Cliente extends EntidadeBase {
	
	public Cliente(String nome, String cpf, String email) {
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
	}
	
	@NotNull(message="O Nome não pode ser vazio")
	@Size(min=3, max=255, message="O nome deve possuir entre 3 e 255 caracteres")
	@Column(name="nome", nullable=false)
	private String nome;
	
	@NotNull(message="O CPF não pode ser vazio")
	@CPF(message = "O CPF é inválido")
	@Column(name="cpf", nullable=false)
	private String cpf;
	
	@NotNull(message="O E-mail não pode ser vazio")
	@Email(message = "O E-mail é inválido")
	@Column(name="email", nullable=false, unique = true)
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
