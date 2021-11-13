package Modell;

public class Filme {
	public static final String DESCRICAO_PADRAO = "Novo Filme";
	public static final String DATA_PADRAO = "12/12/2020";
	private int id;
	private String nome;
	private String dataDeLancamento;
	private String link;
	private String diretor;
	private String descricao;
	
	public Filme() {
		this.id = 0;
		this.nome = "";
		this.dataDeLancamento = DATA_PADRAO;
		this.link = "";
		this.diretor = "";
		this.descricao = DESCRICAO_PADRAO;
	}
	
	public Filme(int id, String nome, String dataDeLancamento, String link, String diretor, String descricao){
		this.id = id;
		this.nome = nome;
		this.dataDeLancamento = dataDeLancamento;
		this.link = link;
		this.diretor = diretor;
		this.descricao = descricao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getDataDeLancamento() {
		return dataDeLancamento;
	}

	public void setDataDeLancamento(String dataDeLancamento) {
		this.dataDeLancamento = dataDeLancamento;
	}
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDiretor() {
		return diretor;
	}

	public void setDiretor(String diretor) {
		this.diretor = diretor;
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
		return "Filme [ id = " + id + ", nome = " + nome + ", dataDeLancamento = " + dataDeLancamento +
				", link = " + link + ", diretor = " + diretor + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		return (this.getId() == ((Filme) obj).getId());
	}	
}