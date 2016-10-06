package senai.systock.exceptions;

public class ValidationException extends Exception {

	private static final long serialVersionUID = -8858542963283669131L;
	
	private String[] mensagens;

	public ValidationException(String... mensagens) {
		this.mensagens = mensagens;
	}

	public String[] getMensagens() {
		return mensagens;
	}

}
