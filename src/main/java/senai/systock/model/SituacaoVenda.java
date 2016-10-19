package senai.systock.model;

public enum SituacaoVenda {

	CONCLUIDA("Concluída"),
	ABERTA("Aberta"),
	CANCELADA("Cancelada"),
	DEVOLVIDA("Devolvida"),
	TROCADA("Trocada");

	private SituacaoVenda(String descricao) {
		this.descricao = descricao;
	}
	
	private String descricao;
	
	public String getDescricao() {
		return descricao;
	}
	
}
