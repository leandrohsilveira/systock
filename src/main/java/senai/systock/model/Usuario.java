package senai.systock.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="usuario")
@SequenceGenerator(name="sequence_gen", sequenceName="usuario_seq", initialValue=1, allocationSize=1)
public class Usuario extends EntidadeBase {
	
	public Usuario() {
	}
	
	public Usuario(String login, String senha, Funcionario funcionario) {
		this.login = login;
		this.senha = senha;
		this.funcionario = funcionario;
	}
	
    public interface SemSenhaView extends IdView {};
    public interface ComSenhaView extends SemSenhaView {};
    
    @NotNull(message="O login é obrigatório")
    @Pattern(regexp="^[a-zA-Z]+(_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9])*$", message="O login deve começar com pelo menos um caracter alfabetico e ter entre 3 e 16 caracteres alfanumericos")
    @Size(min = 3, max = 16, message="O login deve possuir entre 3 e 16 caracteres")
    @Column(name="login", length=16, nullable=false, unique=true, updatable=false)
    @JsonView(SemSenhaView.class)
	private String login;
	
    @NotNull(message="A senha é obrigatória")
    @Size(min = 8, max = 32, message="A senha deve possuir entre 8 e 32 caracteres")
    @Column(name="senha", length=32, nullable=false)
    @JsonView(ComSenhaView.class)
	private String senha;
    
    @NotNull
    @OneToOne(fetch=FetchType.EAGER, optional=false)
    @PrimaryKeyJoinColumn(name="funcionario_id")
    @JsonView(SemSenhaView.class)
    private Funcionario funcionario;

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
	
}