package senai.systock.model;

import java.security.SecureRandom;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonView;

import senai.systock.model.EntidadeBase.Public;

@Entity
@Table(name="usuario")
@SequenceGenerator(name="sequence_gen", sequenceName="usuario_seq", initialValue=1, allocationSize=1)
public class Usuario extends EntidadeBase {
	
	public Usuario() {
	}
	
	public Usuario(String login, String senha, Funcionario funcionario) {
		this.setAtivo(true);
		this.login = login;
		this.senha = senha;
		this.funcionario = funcionario;
	}
	
	public static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();;
	
    
	@JsonView(Public.class)
    @NotNull(message="O login é obrigatório")
    @Pattern(regexp="^[a-zA-Z]+(_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9])*$", message="O login deve começar com pelo menos um caracter alfabetico e ter entre 3 e 16 caracteres alfanumericos")
    @Size(min = 3, max = 16, message="O login deve possuir entre 3 e 16 caracteres")
    @Column(name="login", length=16, nullable=false, unique=true, updatable=false)
	private String login;
	
    @NotNull(message="A senha é obrigatória")
    @Size(min = 8, max = 32, message="A senha deve possuir entre 8 e 32 caracteres")
    @Transient
	private String senha;
    
    @Column(name="secret", nullable=false)
    private String secret;
    
	@NotNull
    @OneToOne(fetch=FetchType.EAGER, optional=false)
	@JoinColumn(name="funcionario_id", nullable=false, unique=true)
	@JsonView(Public.class)
    private Funcionario funcionario;

	@PrePersist
	private void prePersist() {
		secret = passwordEncoder.encode(senha);
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getSecret() {
		return secret;
	}
	
	public void setSecret(String secret) {
		this.secret = secret;
	}
	
}