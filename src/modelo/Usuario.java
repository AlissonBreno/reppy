package modelo;

public class Usuario {
	private Integer id;
	private String login;
	private String senha; 
	private String Nome;
	private String Telefone;
	private TipoUsuario TipoUsuario;

	public Usuario() {
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		Nome = nome;
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		Telefone = telefone;
	}

	public TipoUsuario getTipoUsuario() {
		return TipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		TipoUsuario = tipoUsuario;
	}
	
}