package Modell;

public class Genero {
	public static final String DESCRICAO_PADRAO = "Novo Genero";
	private String nome;
	private String descricao;
	
	public Genero() {
		this.nome = "";
		this.descricao = DESCRICAO_PADRAO;
	}
	
	public Genero(String nome, String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		if (descricao.length() >= 3)
			this.descricao = descricao;
	}
	
	@Override
	public String toString() {
		return "Genero [ nome = " + nome + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		return (this.getNome() == ((Genero) obj).getNome());
	}	
}