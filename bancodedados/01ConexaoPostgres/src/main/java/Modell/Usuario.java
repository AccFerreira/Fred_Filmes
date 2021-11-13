package Modell;

public class Usuario {
	public static final String DESCRICAO_PADRAO = "Novo Usuario";
	public static final String DATA_PADRAO = "12/12/2021";
	private String login;
	private String primeiroNome;
	private String ultimoNome;
	private String email;
	private String senha;
	private String dataDeNascimento;
	private String generoPreferido1;
	private String generoPreferido2;
	private String generoPreferido3;
	private String descricao;
	
	public Usuario() {
		this.login = "";
		this.primeiroNome = "";
		this.ultimoNome = "";
		this.email = "";
		this.senha = "";
		this.dataDeNascimento = DATA_PADRAO;
		this.generoPreferido1 = "";
		this.generoPreferido2 = "";
		this.generoPreferido3 = "";
		this.descricao = DESCRICAO_PADRAO;
	}
	
	public Usuario(String login, String primeiroNome, String ultimoNome, String email,
				   String senha, String dataDeNascimento, String generoPreferido1, 
				   String generoPreferido2, String generoPreferido3, String descricao) {
		this.login = login;
		this.primeiroNome = primeiroNome;
		this.ultimoNome = ultimoNome;
		this.email = email;
		this.senha = senha;
		this.dataDeNascimento = dataDeNascimento;
		this.generoPreferido1 = generoPreferido1;
		this.generoPreferido2 = generoPreferido2;
		this.generoPreferido3 = generoPreferido3;
		this.descricao = descricao;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPrimeiroNome() {
		return primeiroNome;
	}

	public void setPrimeiroNome(String primeiroNome) {
		this.primeiroNome = primeiroNome;
	}
	
	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	
	public String getGeneroPreferido1() {
		return generoPreferido1;
	}
	
	public void setGeneroPreferido1(String generoPreferido1) {
		this.generoPreferido1 = generoPreferido1;
	}
	
	public String getGeneroPreferido2() {
		return generoPreferido2;
	}
	
	public void setGeneroPreferido2(String generoPreferido2) {
		this.generoPreferido2 = generoPreferido2;
	}
	
	public String getGeneroPreferido3() {
		return generoPreferido3;
	}
	
	public void setGeneroPreferido3(String generoPreferido3) {
		this.generoPreferido3 = generoPreferido3;
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
		return "Usuario [ login = " + login + ", primeiroNome = " + primeiroNome 
				+ ", ultimoNome = " + ultimoNome + ", email = " + email 
				+ ", senha = " + senha + ", dataDeNascimento = " + dataDeNascimento 
				+ ", generoPreferido1 = " + generoPreferido1 + ", generoPreferido2 = " + generoPreferido2 
				+ ", generoPreferido3 = " + generoPreferido3 + "]";
	}	
	
	@Override
	public boolean equals(Object obj) {
		return (this.getLogin() == ((Usuario) obj).getLogin());
	}	
}