package modelo;

public class Mantimentos {
	private Integer id;
	private String nome;
	private String validade;
	private TipoMantimentos TipoMantimentos;
	private LocalMantimentos LocalMantimentos;
	private Usuario Usuario;
	
	public Mantimentos() {}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValidade() {
		return validade;
	}
	public void setValidade(String validade) {
		this.validade = validade;
	}
	public TipoMantimentos getTipoMantimentos() {
		return TipoMantimentos;
	}
	public void setTipoMantimentos(TipoMantimentos tipoMantimentos) {
		TipoMantimentos = tipoMantimentos;
	}
	public LocalMantimentos getLocalMantimentos() {
		return LocalMantimentos;
	}
	public void setLocalMantimentos(LocalMantimentos localMantimentos) {
		LocalMantimentos = localMantimentos;
	}
	public Usuario getUsuario() {
		return Usuario;
	}
	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}
	
}
