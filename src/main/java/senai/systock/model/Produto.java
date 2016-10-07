package senai.systock.model;
public class Produto {

    private String descricao;
    private Integer quantidade;
    private Float preco;

    public Produto(String descricao, Float preco, Integer quantidade) {
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }
        
    public void cadastrar() {
    	
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

}
