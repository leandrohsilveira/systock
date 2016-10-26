package senai.systock.json;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import senai.systock.model.Validatable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginJson extends Validatable {
	
	public LoginJson() {
	}
	
	public LoginJson(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
	}

	@NotBlank(message="O login é obrigatório")
	private String login;
	
	@NotBlank(message="A senha é obrigatória")
	private String senha;

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
