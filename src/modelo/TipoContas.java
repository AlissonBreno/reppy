package modelo;

public class TipoContas {
	private Integer id;
	private String Tipo;
	private String Credor;
	
	public TipoContas() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getCredor() {
		return Credor;
	}

	public void setCredor(String credor) {
		Credor = credor;
	}

}