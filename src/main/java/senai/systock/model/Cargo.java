package senai.systock.model;

public enum Cargo {
	
	ADMINISTRADOR("Administrador"),
	GERENTE("Gerente"),
	VENDEDOR("Vendedor");
	
	private Cargo(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
}
