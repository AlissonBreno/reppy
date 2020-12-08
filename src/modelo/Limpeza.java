package modelo;

public class Limpeza {
	private Integer id;
	private String DataMarcada;
	private Usuario Usuario;
	private TipoLimpeza TipoLimpeza;

	public Limpeza() {
	}
	

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getDataMarcada() {
		return DataMarcada;
	}


	public void setDataMarcada(String dataMarcada) {
		DataMarcada = dataMarcada;
	}


	public Usuario getUsuario() {
		return Usuario;
	}


	public void setUsuario(Usuario usuario) {
		Usuario = usuario;
	}


	public TipoLimpeza getTipoLimpeza() {
		return TipoLimpeza;
	}


	public void setTipoLimpeza(TipoLimpeza tipoLimpeza) {
		TipoLimpeza = tipoLimpeza;
	}


	
}